package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state

data class AddItemDialogState(
    val description: String = "",
    val quantity: String = "1",
    val isQuantityInvalidInput: Boolean = false,
    val price: String = "",
    val subtotal: String = "0",
    val isSubtotalInputError: Boolean = false
)
