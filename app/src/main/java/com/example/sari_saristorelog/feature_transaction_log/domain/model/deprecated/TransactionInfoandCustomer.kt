package com.example.sari_saristorelog.feature_transaction_log.domain.model

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
