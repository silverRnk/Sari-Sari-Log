package com.example.sari_saristorelog.data.repository

import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.feature_transaction_log.domain.repository.LoggerRepository
import kotlinx.coroutines.flow.Flow

class FakeLoggerRepository: LoggerRepository
{
    override suspend fun insertTransaction(info: TransactionInfo, items: List<Items>) {
        TODO("Not yet implemented")
    }

    override suspend fun getTransaction(id: Long): Transaction? {
        TODO("Not yet implemented")
    }

    override fun getTransInfo(): Flow<List<TransactionInfo>> {
        TODO("Not yet implemented")
    }
}