package com.example.sari_saristorelog.feature_transaction_log.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionInfo(
    @PrimaryKey(autoGenerate = true)
    val transactionId: Long? = null,
    var customerId: Int,
    val createdDate: Long,
    var editedDate: Long? = null,
    val confirmedDate: Long? = null,
    val isConfirmed: Boolean = false,
    val total: Double
)
