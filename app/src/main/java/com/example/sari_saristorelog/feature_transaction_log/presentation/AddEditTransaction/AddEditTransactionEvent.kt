package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction

import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import java.time.LocalDate
import java.time.LocalTime

sealed class AddEditTransactionEvent{
    data class OnNameTextFieldChange(val name: String): AddEditTransactionEvent()
    object OnToggleIconSelection: AddEditTransactionEvent()
    data class OnDeleteItem(val itemIndex: Int): AddEditTransactionEvent()
    object OnAddTransaction: AddEditTransactionEvent()
    data class OnChangeDate(val date: LocalDate): AddEditTransactionEvent()
    data class OnChangeTime(val time: LocalTime): AddEditTransactionEvent()
    object OnToggleDate: AddEditTransactionEvent()

}
