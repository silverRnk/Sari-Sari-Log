package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sari_saristorelog.core.data.util.CustomerIcons
import com.example.sari_saristorelog.feature_transaction_log.domain.model.InvalidTransaction
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.use_cases.TransactionLogUseCases
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state.AddItemDialogState
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state.CustomerInfoState
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state.DateState
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state.ItemState
import com.example.sari_saristorelog.feature_transaction_log.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditTransactionViewModel @Inject constructor(
    private val useCases: TransactionLogUseCases
): ViewModel() {

    private val _customerInfoState = mutableStateOf(CustomerInfoState())
    val customerInfoState: State<CustomerInfoState> = _customerInfoState

    private val _itemState = mutableStateOf(ItemState())
    val itemState: State<ItemState> = _itemState

/*    private val _dateState = mutableStateOf(DateState())
    val dateState: State<DateState> = _dateState*/

    private val _addItemDialogState = mutableStateOf(AddItemDialogState())
    val addItemDialogState: State<AddItemDialogState> = _addItemDialogState

    private val _uiState = MutableSharedFlow<UiState>()
    val uiState = _uiState.asSharedFlow()

    fun onEvent(event: AddEditTransactionEvent){
        when(event){
            is AddEditTransactionEvent.OnNameTextFieldChange -> {
                _customerInfoState.value = customerInfoState.value.copy(
                    info = customerInfoState.value.info.copy(
                        customerName = event.name
                    ))
            }
            is AddEditTransactionEvent.OnToggleIconSelection -> {
                val nextIcon = CustomerIcons.icons.indexOf(customerInfoState.value.info.customerIcon) + 1
                val iconsCount = CustomerIcons.icons.size
                _customerInfoState.value = customerInfoState.value.copy(
                    info = customerInfoState.value.info.copy(
                        customerIcon = CustomerIcons.icons.elementAt(
                            if(nextIcon > iconsCount - 1) 0 else nextIcon)
                    )
                )
            }
            is AddEditTransactionEvent.OnDeleteItem -> {
                if ((event.itemIndex+1) <= itemState.value.items.size){
                    var newList = itemState.value.items.filterIndexed { index, _ ->
                        index != event.itemIndex}
                    var newTotal = 0.0
                    newList.forEach {
                        newTotal += it.subtotal
                    }

                    _itemState.value = itemState.value.copy(
                        items = newList,
                        total = newTotal
                    )
                }
            }
            is AddEditTransactionEvent.OnAddTransaction -> {
                viewModelScope.launch {
                    try{
                        useCases.addTransaction(
                            info = customerInfoState.value.info,
                            items = itemState.value.items)
                    }catch (e: InvalidTransaction){
                        _uiState.emit(UiState.ShowSnackBar(e.message?: ""))
                    }

                }
            }
            is AddEditTransactionEvent.OnChangeDate -> {
                val newCreatedDate = LocalDateTime.of(event.date, customerInfoState.value.info.createdDate!!.toLocalTime())

                _customerInfoState.value = customerInfoState.value.copy(
                    info = customerInfoState.value.info.copy(createdDate = newCreatedDate)
                )
            }
            is AddEditTransactionEvent.OnChangeTime -> {
                val newCreatedDate = LocalDateTime.of(customerInfoState.value.info.createdDate!!.toLocalDate(), event.time)
                _customerInfoState.value = customerInfoState.value.copy(
                    info = customerInfoState.value.info.copy(createdDate = newCreatedDate))
            }
            is AddEditTransactionEvent.OnToggleDate -> {
                _customerInfoState.value = customerInfoState.value.copy(
                    isDateStatusVisible = !customerInfoState.value.isDateStatusVisible
                )
            }
        }
    }


    fun dialogEvent(event: AddEditDialogEvent){
        when(event){
            is AddEditDialogEvent.OnDescriptionChange -> {
                _addItemDialogState.value = addItemDialogState.value.copy(
                    description = event.description
                )
            }
            is AddEditDialogEvent.OnQuantityChange -> {
                _addItemDialogState.value = addItemDialogState.value.copy(
                    quantity = event.quantity,
                    isQuantityInvalidInput = !(event.quantity.isDigitsOnly() && event.quantity.isNotEmpty())
                )

            }
            is AddEditDialogEvent.OnPriceChange -> {
                if (isDoubleString(event.price)){
                    _addItemDialogState.value = addItemDialogState.value.copy(
                        price = event.price
                    )
                }
            }
            is AddEditDialogEvent.OnSubtotalChange -> {
                if (isDoubleString(event.subtotal)){
                    _addItemDialogState.value = addItemDialogState.value.copy(
                        subtotal = event.subtotal,
                        isSubtotalInvalidInput = event.subtotal.isEmpty())
                }else if(event.subtotal.isEmpty()){
                    _addItemDialogState.value = addItemDialogState.value.copy(
                        subtotal = event.subtotal,
                        isSubtotalInvalidInput = event.subtotal.isEmpty())
                }
            }
            is AddEditDialogEvent.OnPositiveButton -> {
                viewModelScope.launch {
                    try {
                        val itemList = itemState.value.items.toMutableList()
                        val newItem = Items(
                            description = _addItemDialogState.value.description,
                            quantity = _addItemDialogState.value.quantity.toInt(),
                            price = if (_addItemDialogState.value.price.isEmpty()) null else _addItemDialogState.value.price.toDouble(),
                            subtotal = _addItemDialogState.value.subtotal.toDouble()
                        )
                        if (addItemDialogState.value.itemIndex != -1){
                            itemList[addItemDialogState.value.itemIndex] = newItem
                            var newTotal = customerInfoState.value.info.total
                            itemList.forEach {
                                newTotal += it.subtotal
                            }
                            _itemState.value = itemState.value.copy(
                                items = itemList
                            )
                            _customerInfoState.value = customerInfoState.value.copy(
                                info = customerInfoState.value.info.copy(
                                    total = newTotal
                                )
                            )
                        }else{
                            _itemState.value = itemState.value.copy(items = itemList.plus(newItem))
                            _customerInfoState.value = customerInfoState.value.copy(
                                info = customerInfoState.value.info.copy(
                                total = customerInfoState.value.info.total.plus(addItemDialogState.value.subtotal.toDouble())
                                )
                            )
                        }



                    }catch (e: Exception){
                        _uiState.emit(UiState.ShowSnackBar("Invalid Input"))
                    }
                    resetAddItemState()
                }

            }
            is AddEditDialogEvent.OnAddEditItem -> {
                if (event.index != -1){
                    _addItemDialogState.value = addItemDialogState.value.copy(
                        itemIndex = event.index,
                        description = itemState.value.items[event.index].description,
                        quantity = itemState.value.items[event.index].quantity.toString(),
                        price = itemState.value.items[event.index].price?.toString()?: "",
                        subtotal = itemState.value.items[event.index].subtotal.toString()
                    )
                }else{
                    resetAddItemState()
                }
            }
            else -> {
                resetAddItemState()
            }
        }
    }

/*    private fun validateStringDouble(double: String): String{
        val trimDouble = double.trimStart("0".toCharArray()[0])

        return if (trimDouble.isNotEmpty() || trimDouble.isNotBlank()){
            trimDouble
        }else{
            double
        }
    }*/

    private fun isDoubleString(digit: String): Boolean{
        val dotCount = digit.count {
            it == ".".toCharArray()[0]
        }
        return digit.length <= 10 && digit.isNotEmpty() &&
                dotCount <= 1 &&
                (digit.last().isDigit() ||
                        digit.last() == ".".toCharArray()[0])
    }

    private fun resetAddItemState(){
        _addItemDialogState.value = AddItemDialogState()
    }
}