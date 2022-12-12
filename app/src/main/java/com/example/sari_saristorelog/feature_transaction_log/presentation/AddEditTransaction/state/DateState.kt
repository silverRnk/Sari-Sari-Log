package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state

import java.time.LocalDate
import java.time.LocalTime

data class DateState(
    val currentDate: LocalDate = LocalDate.now(),
    val currentTime: LocalTime = LocalTime.now()
)
