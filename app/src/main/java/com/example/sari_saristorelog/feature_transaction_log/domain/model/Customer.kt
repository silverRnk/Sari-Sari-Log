package com.example.sari_saristorelog.feature_transaction_log.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true)
    var customerId: Long? = null,
    var nickName: String,
    var firstName: String?,
    var middleName: String?,
    var lastName: String?,
    var avatar: String
)
