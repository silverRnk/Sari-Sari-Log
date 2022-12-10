package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction

import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items

sealed class AddEditTransactionEvent{
    data class OnNameTextFieldChange(val name: String): AddEditTransactionEvent()
    object OnToggleIconSelection: AddEditTransactionEvent()
    object OnAddItem: AddEditTransactionEvent()
    data class OnDeleteItem(val itemIndex: Int): AddEditTransactionEvent()

}
