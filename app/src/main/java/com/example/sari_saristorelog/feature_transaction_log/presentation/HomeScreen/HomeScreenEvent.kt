package com.example.sari_saristorelog.feature_transaction_log.presentation.HomeScreen

sealed class HomeScreenEvent{
    data class OnSelectItem(val transactionId: Long): HomeScreenEvent()
    data class OnSearchValueChange(val searchBox: String): HomeScreenEvent()
    data class OnFilterDate(val toDate: Long?, val fromDate: Long): HomeScreenEvent()
}
