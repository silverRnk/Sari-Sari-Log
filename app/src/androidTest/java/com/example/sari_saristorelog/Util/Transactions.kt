package com.example.sari_saristorelog.Util

import com.example.sari_saristorelog.data.Customer
import com.example.sari_saristorelog.data.transaction.Items
import com.example.sari_saristorelog.data.transaction.Transaction
import com.example.sari_saristorelog.data.transaction.TransactionInfo

object Transactions {

    val customerInfo = Customer(
        customerId = 1,
        nickName = "Patrick",
        firstName = "Patrick",
        middleName = "Diego",
        lastName = "Bautista",
        avatar = ""
    )

    var transactioninfo1 = TransactionInfo(transactionId = 1, customerId = 1, createdDate = "July", total = 8.00)
    val item1 = Items(
            itemId = 1,
            transactionId = 1,
            description = "items1",
            quantity = 1,
            price = 2.00,
            subtotal = 2.00,)

    val item2 = Items(
            itemId = 2,
            transactionId = 1,
            description = "items2",
            quantity = 2,
            price = 3.00,
            subtotal = 6.00,)

    val transaction1 = Transaction(transactionInfo = transactioninfo1, items = listOf(item1,item2))
}