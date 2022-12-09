package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import java.time.LocalDate

sealed class HomeScreenEvent{
    data class OnSelectItem(val transactionId: Int): HomeScreenEvent()
    data class OnSearchValueChange(val searchBox: String): HomeScreenEvent()
    data class OnDateFilter(val fromDate: Long, val toDate: Long, val isEnable: Boolean): HomeScreenEvent()
    data class OnFromFilterDateSelected(val date: LocalDate): HomeScreenEvent()
    data class OnToFilterDateSelected(val date: LocalDate): HomeScreenEvent()
    object OnFilterVisibilityToggle: HomeScreenEvent()
}
