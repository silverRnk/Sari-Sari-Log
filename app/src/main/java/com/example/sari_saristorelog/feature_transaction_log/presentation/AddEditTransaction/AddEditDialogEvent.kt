package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction

import androidx.compose.ui.text.input.TextFieldValue

sealed class AddEditDialogEvent{
    data class OnDescriptionChange(val description: String): AddEditDialogEvent()
    data class OnQuantityChange(val quantity: TextFieldValue): AddEditDialogEvent()
    data class OnPriceChange(val price: TextFieldValue): AddEditDialogEvent()
    data class OnSubtotalChange(val subtotal: TextFieldValue): AddEditDialogEvent()
    object OnPositiveButton: AddEditDialogEvent()
    object OnCancel: AddEditDialogEvent()
}
