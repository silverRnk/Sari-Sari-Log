package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state

import androidx.compose.ui.text.input.TextFieldValue

data class AddItemDialogState(
    val description: String = "",
    val quantity: TextFieldValue = TextFieldValue(text = "0"),
    val price: TextFieldValue = TextFieldValue(text = "0"),
    val subtotal: TextFieldValue = TextFieldValue(text = "0")
)
