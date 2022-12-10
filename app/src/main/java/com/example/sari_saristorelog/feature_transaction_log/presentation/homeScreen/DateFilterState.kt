package com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen

import com.example.sari_saristorelog.feature_transaction_log.domain.util.FilterBy
import java.time.*

data class DateFilterState(
    val fromDate: LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(0), ZoneId.of("UTC")),
    val toDate: LocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59)),
    val isEnable: Boolean = false
)
