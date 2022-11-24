package com.example.sari_saristorelog.data.transaction

import androidx.room.Embedded
import androidx.room.Relation


data class Transaction(
    @Embedded val transactionInfo: TransactionInfo,
    @Relation(
        parentColumn ="transactionId",
        entityColumn = "transactionId")
    val items: List<Items>
)
