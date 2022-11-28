package com.example.sari_saristorelog.repository

import com.example.sari_saristorelog.data.transaction.Items
import com.example.sari_saristorelog.data.transaction.Transaction
import com.example.sari_saristorelog.data.transaction.TransactionInfo
import com.example.sari_saristorelog.data.transaction.TransactionInfoAndCustomer
import com.example.sari_saristorelog.util.QueryOrder

interface LoggerRepository {

    suspend fun insertTransaction(info: TransactionInfo, items: List<Items>)

    suspend fun getTransaction(id: Long): Transaction?

    suspend fun getTransInfoWithCustomer(order: QueryOrder): List<TransactionInfoAndCustomer>

    suspend fun findTransInfoWithCustomerByName(name: String, list: List<TransactionInfoAndCustomer>): List<TransactionInfoAndCustomer>

}