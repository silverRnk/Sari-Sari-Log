package com.example.sari_saristorelog.feature_transaction_log.domain.util

sealed class FilterBy(val order: QueryOrder){
    data class Name(val name: String, val queryOrder: QueryOrder): FilterBy(queryOrder)
    data class Date(val fromDate: Long = 0,
                    val toDate: Long = Long.MAX_VALUE, val queryOrder: QueryOrder): FilterBy(queryOrder)
    data class DateAndName(val name: String,
                          val fromDate: Long = 0,
                          val toDate: Long = Long.MAX_VALUE,
                          val queryOrder: QueryOrder): FilterBy(queryOrder)
    class NoFilter(order: QueryOrder): FilterBy(order)
}
