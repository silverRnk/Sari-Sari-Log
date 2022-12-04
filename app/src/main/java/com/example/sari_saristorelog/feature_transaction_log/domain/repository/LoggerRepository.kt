package com.example.sari_saristorelog.feature_transaction_log.domain.repository

import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.data.transaction.TransactionInfoAndCustomer
import com.example.sari_saristorelog.feature_transaction_log.domain.util.QueryOrder

interface LoggerRepository {

    suspend fun insertTransaction(info: TransactionInfo, items: List<Items>)

    suspend fun getTransaction(id: Long): Transaction?

    suspend fun getTransInfoWithCustomer(order: QueryOrder): List<TransactionInfoAndCustomer>

    suspend fun findTransInfoWithCustomerByName(name: String, list: List<TransactionInfoAndCustomer>): List<TransactionInfoAndCustomer>

}