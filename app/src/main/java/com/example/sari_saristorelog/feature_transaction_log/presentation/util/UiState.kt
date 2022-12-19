package com.example.sari_saristorelog.feature_transaction_log.presentation.util

sealed class UiState{
    data class OnNavigate(val route: String): UiState()
    data class ShowSnackBar(val message: String): UiState()
}
