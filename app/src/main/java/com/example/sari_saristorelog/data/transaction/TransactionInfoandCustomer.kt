package com.example.sari_saristorelog.data.transaction

import androidx.room.Embedded
import androidx.room.Relation
import com.example.sari_saristorelog.data.Customer

data class TransactionInfoAndCustomer(
    val transactionId: Long?,
    val nickName: String,
    val avatar: String,
    val createdDate: Long,
    val confirmedDate: Long?,
    var editedDate: Long?,
    val isConfirmed: Boolean,
    val total: Double
)
