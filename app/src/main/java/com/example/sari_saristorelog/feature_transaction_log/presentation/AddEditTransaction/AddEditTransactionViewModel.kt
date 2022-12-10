package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sari_saristorelog.core.data.util.CustomerIcons
import com.example.sari_saristorelog.feature_transaction_log.domain.model.deprecated.Customer
import com.example.sari_saristorelog.feature_transaction_log.domain.use_cases.TransactionLogUseCases
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state.CustomerInfoState
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
                if (event.itemIndex <= itemState.value.items.size){
                    _itemState.value = itemState.value.copy(
                        items = itemState.value.items.filterIndexed { index, _ ->
                        index != event.itemIndex
                    })
                }
            }
            is AddEditTransactionEvent.OnAddTransaction -> {

            }
        }
    }
}