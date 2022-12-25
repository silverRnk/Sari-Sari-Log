package com.example.sari_saristorelog.feature_transaction_log.domain.model

import androidx.room.Embedded
import androidx.room.Relation


data class Transaction(
    @Embedded val transactionInfo: TransactionInfo,
    @Relation(
        parentColumn ="transactionId",
        entityColumn = "transactionId")
    val items: List<Items>
)

class InvalidTransaction(message: String): Exception(message)