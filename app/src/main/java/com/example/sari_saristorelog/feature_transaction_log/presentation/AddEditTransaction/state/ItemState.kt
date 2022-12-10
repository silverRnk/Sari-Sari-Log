package com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.state

import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items

data class ItemState(
    val items: List<Items> = listOf(),
    val total: Double = 00.00
)
