package com.example.sari_saristorelog.feature_transaction_log.presentation.ConfirrmTransaction

sealed class ConfirmTransactionEvent{
    object OnConfirmed: ConfirmTransactionEvent()
    object OnDelete: ConfirmTransactionEvent()
    object OnEdit: ConfirmTransactionEvent()
}
