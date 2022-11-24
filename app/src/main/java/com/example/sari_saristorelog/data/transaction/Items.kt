package com.example.sari_saristorelog.data.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Items(
    @PrimaryKey(autoGenerate = true)
    val itemId: Long? = null,
    var transactionId: Long? = null,
    var description: String,
    var quantity: Int,
    var price: Double,
    var subtotal: Double,
    var isPaid: Boolean = false
)
