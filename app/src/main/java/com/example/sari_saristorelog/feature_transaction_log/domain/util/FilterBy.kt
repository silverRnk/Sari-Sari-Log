package com.example.sari_saristorelog.feature_transaction_log.domain.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

sealed class FilterBy(val order: QueryOrder){
    data class Name(val name: String, val queryOrder: QueryOrder): FilterBy(queryOrder)
    data class Date(val fromDate: LocalDateTime = LocalDateTime.MIN,
                    val toDate: LocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59)),
                    val queryOrder: QueryOrder): FilterBy(queryOrder)
    data class DateAndName(val name: String,
                           val fromDate: LocalDateTime = LocalDateTime.MIN,
                           val toDate: LocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59)),
                           val queryOrder: QueryOrder): FilterBy(queryOrder)
    class NoFilter(order: QueryOrder): FilterBy(order)
}
