package com.example.sari_saristorelog.ui.homeScreen

sealed class HomeScreenEvent{
    data class OnSelectItem(val transactionId: Long): HomeScreenEvent()
    data class OnSearchValueChange(val searchBox: String): HomeScreenEvent()
}
