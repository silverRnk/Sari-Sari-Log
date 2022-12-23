package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sari_saristorelog.core.data.util.CustomerIcons
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
import javax.inject.Inject

@HiltViewModel
class AddEditTransactionViewModel @Inject constructor(
    private val useCases: TransactionLogUseCases
): ViewModel() {

    private val _customerInfoState = mutableStateOf(CustomerInfoState())
    val customerInfoState: State<CustomerInfoState> = _customerInfoState

    private val _itemState = mutableStateOf(ItemState())
    val itemState: State<ItemState> = _itemState

    private val _dateState = mutableStateOf(DateState())
    val dateState: State<DateState> = _dateState

    private val _addItemDialogState = mutableStateOf(AddItemDialogState())
    val addItemDialogState: State<AddItemDialogState> = _addItemDialogState

    private val _uiState = MutableSharedFlow<UiState>()
    val uiState = _uiState.asSharedFlow()

    fun onEvent(event: AddEditTransactionEvent){
        when(event){
            is AddEditTransactionEvent.OnNameTextFieldChange -> {
                _customerInfoState.value = customerInfoState.value.copy(
                    name = event.name)
            }
            is AddEditTransactionEvent.OnToggleIconSelection -> {
                //@Todo Test Event for releiblity
                val nextIcon = CustomerIcons.icons.indexOf(customerInfoState.value.customerIcon) + 1
                val iconsCount = CustomerIcons.icons.size
                _customerInfoState.value = customerInfoState.value.copy(
                    customerIcon = CustomerIcons.icons.elementAt(
                        if(nextIcon > iconsCount - 1) 0 else nextIcon
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

                useCases.addTransaction(info = )
            }
            is AddEditTransactionEvent.OnChangeDate -> {
                _dateState.value = dateState.value.copy(
                    currentDate = event.date
                )
            }
            is AddEditTransactionEvent.OnChangeTime -> {
                _dateState.value = dateState.value.copy(
                    currentTime = event.time
                )
            }
            is AddEditTransactionEvent.OnToggleDate -> {
                _dateState.value = dateState.value.copy(isVisible = !dateState.value.isVisible)
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
                            var newTotal = 0.0
                            itemList.forEach {
                                newTotal += it.subtotal
                            }
                            _itemState.value = itemState.value.copy(
                                items = itemList,
                                total = newTotal
                            )
                        }else{
                            _itemState.value = itemState.value.copy(items = itemList.plus(newItem),
                                total = itemState.value.total.plus(newItem.subtotal))
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

    private fun validateStringDouble(double: String): String{
        val trimDouble = double.trimStart("0".toCharArray()[0])

        return if (trimDouble.isNotEmpty() || trimDouble.isNotBlank()){
            trimDouble
        }else{
            double
        }
    }

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