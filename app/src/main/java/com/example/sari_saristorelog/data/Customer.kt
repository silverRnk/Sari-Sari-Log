package com.example.sari_saristorelog.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey
    val customerId: Int,
    var nickName: String,
    var firstName: String?,
    var middleName: String?,
    var lastName: String?,
    var avatar: String
)
