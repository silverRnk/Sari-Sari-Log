package com.example.sari_saristorelog.feature_transaction_log.presentation.ConfirrmTransaction

import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction

data class ConfirmTransactionState(
    val isConfirmed: Boolean = false,
    val isConfirmButtonEnable: Boolean = false
)
