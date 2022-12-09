package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import com.example.sari_saristorelog.feature_transaction_log.domain.util.FilterBy
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

data class DateFilterState(
    val fromDate: Long = 0,
    val toDate: Long = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).toEpochSecond(
        ZoneOffset.UTC),
    val isEnable: Boolean = false
)
