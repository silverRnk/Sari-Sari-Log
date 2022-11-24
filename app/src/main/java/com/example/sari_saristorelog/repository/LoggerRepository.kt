package com.example.sari_saristorelog.repository

import com.example.sari_saristorelog.data.transaction.Items
import com.example.sari_saristorelog.data.transaction.Transaction
import com.example.sari_saristorelog.data.transaction.TransactionInfo

interface LoggerRepository {

    suspend fun insertTransaction(info: TransactionInfo, items: List<Items>)

    suspend fun getTransaction(id: Long): Transaction?

}