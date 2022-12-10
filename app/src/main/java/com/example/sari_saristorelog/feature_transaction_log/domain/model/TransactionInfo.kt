package com.example.sari_saristorelog.feature_transaction_log.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class TransactionInfo(
    @PrimaryKey(autoGenerate = true)
    val transactionId: Long? = null,
    val customerName: String,
    val customerIcon: Int,
    val createdDate: LocalDateTime?,
    var editedDate: LocalDateTime? = null,
    val confirmedDate: LocalDateTime? = null,
    val isConfirmed: Boolean = false,
    val total: Double
)
