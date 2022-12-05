package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

sealed class HomeScreenEvent{
    data class OnSelectItem(val transactionId: Long): HomeScreenEvent()
    data class OnSearchValueChange(val searchBox: String): HomeScreenEvent()
}
