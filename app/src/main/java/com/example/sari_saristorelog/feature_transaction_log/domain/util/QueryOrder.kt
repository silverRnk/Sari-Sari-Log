package com.example.sari_saristorelog.feature_transaction_log.domain.util

sealed class QueryOrder{
    object Asc: QueryOrder()
    object Desc: QueryOrder()
}


