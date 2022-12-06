package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

data class DateFilterState(
    val FromDate: Long = -1,
    val toDate: Long = Long.MAX_VALUE,
    val isDateFilterSelected: Boolean = false
)
