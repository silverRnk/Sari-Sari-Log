package com.example.sari_saristorelog.Util

import com.example.sari_saristorelog.data.transaction.Items
import com.example.sari_saristorelog.data.transaction.Transaction
import com.example.sari_saristorelog.data.transaction.TransactionInfo

object Transactions {

    val transaction1 = Transaction(
        transactionInfo = TransactionInfo(customerId = 1, createdDate = "July", total = 8.00),
        items = listOf(Items(
            description = "items1",
            quantity = 1,
            price = 2.00,
            subtotal = 2.00,
        ),
            Items(
                description = "items2",
                quantity = 2,
                price = 3.00,
                subtotal = 6.00,
            ))
    )
}