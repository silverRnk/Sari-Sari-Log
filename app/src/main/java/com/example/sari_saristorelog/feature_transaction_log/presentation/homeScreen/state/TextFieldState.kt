package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.state

data class TextFieldState(
    val text: String = "",
    val hint: String = "Search name ...",
    val isEnable: Boolean = false,
    val isPlaceHolderVisible: Boolean = true
)
