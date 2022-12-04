package com.example.sari_saristorelog.data.transaction

data class TransactionInfoAndCustomer(
    val transactionId: Long?,
    val nickName: String,
    val avatar: Int,
    val createdDate: Long,
    val confirmedDate: Long?,
    var editedDate: Long?,
    val isConfirmed: Boolean,
    val total: Double
)
