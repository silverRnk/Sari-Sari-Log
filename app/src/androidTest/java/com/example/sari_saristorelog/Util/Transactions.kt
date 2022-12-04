package com.example.sari_saristorelog.Util

import com.example.sari_saristorelog.feature_transaction_log.domain.model.deprecated.Customer
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfoAndCustomer

object Transactions {

    val customerInfo = Customer(
        customerId = 1,
        nickName = "Patrick",
        firstName = "Patrick",
        middleName = "Diego",
        lastName = "Bautista",
        avatar = ""
    )

    var transactioninfo1 = TransactionInfo(transactionId = 1, customerId = 1, createdDate = 1, total = 8.00)
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

    val customer2 = Customer(
        customerId = 2,
        nickName = "Patrick2",
        firstName = "Patrick2",
        middleName = "Diego2",
        lastName = "Bautista2",
        avatar = ""
    )

    val transaction2 = TransactionInfo(transactionId = 2, customerId = 2, createdDate = 2, total = 8.00)
    val transaction3 = TransactionInfo(transactionId = 3, customerId = 2, createdDate = 3, total = 8.00)

    val transWithCustomer1 = listOf(
        TransactionInfoAndCustomer(transactionId=3, nickName="Patrick2", avatar="", createdDate=3, confirmedDate=null, editedDate=null, isConfirmed=false, total=8.0), TransactionInfoAndCustomer(transactionId=2, nickName="Patrick2", avatar="", createdDate=2, confirmedDate=null, editedDate=null, isConfirmed=false, total=8.0), TransactionInfoAndCustomer(transactionId=1, nickName="Patrick", avatar="", createdDate=1, confirmedDate=null, editedDate=null, isConfirmed=false, total=8.0)
    )
}