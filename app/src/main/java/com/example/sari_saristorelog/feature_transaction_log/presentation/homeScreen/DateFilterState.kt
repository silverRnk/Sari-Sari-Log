package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import com.example.sari_saristorelog.feature_transaction_log.domain.util.FilterBy

data class DateFilterState(
    val fromDate: Long = 0,
    val toDate: Long = Long.MAX_VALUE,
    val isEnable: Boolean = false
)
