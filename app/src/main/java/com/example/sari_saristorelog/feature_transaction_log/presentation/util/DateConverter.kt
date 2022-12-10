package com.example.sari_saristorelog.feature_transaction_log.presentation.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object DateConverter {

    fun convertLocalDateTimeToLocalDate(date: LocalDateTime): LocalDate{
        return date.toLocalDate()
    }

    fun convertLocalDateToLocalDateTime(date: LocalDate): LocalDateTime {
        return LocalDateTime.of(date, LocalTime.of(23,59))
    }
}