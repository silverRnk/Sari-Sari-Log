package com.example.sari_saristorelog.data.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionInfo(
    @PrimaryKey(autoGenerate = true)
    val transactionId: Long? = null,
    var customerId: Int,
    val createdDate: String,
    var editedDate: String? = null,
    val confirmedDate: String? = null,
    val isConfirmed: Boolean = false,
    val total: Double
)
