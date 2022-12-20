package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction


sealed class AddEditDialogEvent{
    data class OnDescriptionChange(val description: String): AddEditDialogEvent()
    data class OnQuantityChange(val quantity: String): AddEditDialogEvent()
    data class OnPriceChange(val price: String): AddEditDialogEvent()
    data class OnSubtotalChange(val subtotal: String): AddEditDialogEvent()
    object OnPositiveButton: AddEditDialogEvent()
    object OnCancel: AddEditDialogEvent()
}
