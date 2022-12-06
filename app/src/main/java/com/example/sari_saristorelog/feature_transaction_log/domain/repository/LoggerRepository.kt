package com.example.sari_saristorelog.feature_transaction_log.domain.repository

import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfoAndCustomer
import com.example.sari_saristorelog.feature_transaction_log.domain.util.QueryOrder
import kotlinx.coroutines.flow.Flow

interface LoggerRepository {

    suspend fun insertTransaction(info: TransactionInfo, items: List<Items>)

    suspend fun getTransaction(id: Long): Transaction?

    fun getTransInfo(): Flow<List<TransactionInfo>>


}