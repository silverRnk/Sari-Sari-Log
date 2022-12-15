package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.sari_saristorelog.core.data.util.CustomerIcons
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.model.deprecated.Customer
import com.example.sari_saristorelog.feature_transaction_log.domain.use_cases.TransactionLogUseCases
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state.AddItemDialogState
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state.CustomerInfoState
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state.DateState
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state.ItemState
import dagger.hilt.android.lifecycle.HiltViewModel
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
                        if(nextIcon > iconsCount) 1 else nextIcon
                    )
                )
            }
            is AddEditTransactionEvent.OnAddEditItem -> {
                //@Todo Implement AddEditTransaction OnAddItem
                if(event.itemIndex == -1){

                }else{

                }
            }
            is AddEditTransactionEvent.OnDeleteItem -> {
                if ((event.itemIndex+1) <= itemState.value.items.size){
                    _itemState.value = itemState.value.copy(
                        items = itemState.value.items.filterIndexed { index, _ ->
                        index != event.itemIndex
                    })
                }
            }
            is AddEditTransactionEvent.OnAddTransaction -> {

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
            is AddEditTransactionEvent.OnPositiveButton -> {
                _itemState.value = itemState.value.copy(
                    items = itemState.value.items.plus(event.items)
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
                val text = event.quantity.text
                val price = addItemDialogState.value.price.text
                val quantity:String = if (text.isDigitsOnly()
                                 && !text.isNullOrEmpty() && text.length <= 5){
                                   if (text.contains(Regex("[1-9]"))){
                                       text.trimStart("0".toCharArray()[0])
                                   }else{
                                       text
                                   }
                                 }else if(text.isDigitsOnly()
                                     && !text.isNullOrEmpty() && text.length >= 5){
                                      addItemDialogState.value.quantity.text
                                 } else{ "0" }

                val subtotal = if(price.isNotEmpty() && price.isNotBlank()){
                    (price.toDouble() * quantity.toInt()).toString()
                }else{
                    "0.0"
                }


                _addItemDialogState.value = addItemDialogState.value.copy(
                    quantity = TextFieldValue(text = quantity, selection = TextRange(quantity.length)),
                    subtotal = TextFieldValue(text = subtotal, selection = TextRange(subtotal.length)))
            }
            is AddEditDialogEvent.OnPriceChange -> {
                val text = event.price.text
                var quantity = addItemDialogState.value.quantity.text
                var thisPrice = "0"
                if(text.length <= 5 && text.isNotEmpty() &&
                    (text.last().isDigit() ||
                            text.last() == ".".toCharArray()[0])){
                    val trimText = text.trimStart("0".toCharArray()[0])
                    thisPrice = if (trimText.isNotEmpty() || trimText.isNotBlank()){
                                   trimText
                                }else{
                                   text
                                }
                }

                val thisSubtotal: String = (quantity.toDouble() * thisPrice.toDouble()).toString()


                _addItemDialogState.value = addItemDialogState.value.copy(
                    price = TextFieldValue(text = thisPrice, selection = TextRange(thisPrice.length)),
                    subtotal = TextFieldValue(text = thisSubtotal, selection = TextRange(thisSubtotal.length))
                )
            }
            is AddEditDialogEvent.OnSubtotalChange -> {
                val text = event.subtotal.text
                var quantity = addItemDialogState.value.quantity.text
                var thisPrice = "0"
                var thisSubtotal = "0.0"
                if(text.isNotEmpty() && (text.last().isDigit() ||
                    text.last() == "0".toCharArray()[0]) && text.length <= 5){
                    val trimText = text.trimStart("0".toCharArray()[0])
                    thisSubtotal = if (trimText.isNotEmpty() || trimText.isNotBlank()){
                                      trimText
                                   }else{
                                       text
                                   } }

                if(quantity.toInt() != 0){
                    thisPrice = thisSubtotal.toDouble().div(quantity.toInt()).toString()
                }


                _addItemDialogState.value = addItemDialogState.value.copy(
                    price = TextFieldValue(text = thisPrice, selection = TextRange(thisPrice.length)),
                    subtotal = TextFieldValue(text = thisSubtotal, selection = TextRange(thisSubtotal.length))
                )
            }
            is AddEditDialogEvent.OnPositiveButton -> {
                val itemList = itemState.value.items
                val newItem = Items(
                    description = _addItemDialogState.value.description,
                    quantity = _addItemDialogState.value.quantity.text.toInt(),
                    price = _addItemDialogState.value.price.text.toDouble(),
                    subtotal = _addItemDialogState.value.subtotal.text.toDouble()
                )

                _itemState.value = itemState.value.copy(items = itemList.plus(newItem))
                resetAddItemState()
            }
            else -> {
                resetAddItemState()
            }
        }
    }

    private fun resetAddItemState(){
        _addItemDialogState.value = AddItemDialogState()
    }
}