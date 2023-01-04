package com.example.sari_saristorelog.feature_transaction_log.data.repository

import android.util.Log
import com.example.sari_saristorelog.core.data.repository.ApplicationDao
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfoAndCustomer
import com.example.sari_saristorelog.feature_transaction_log.domain.util.QueryOrder
import com.example.sari_saristorelog.feature_transaction_log.domain.repository.LoggerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class LoggerRepositoryImp @Inject constructor(
    private val dao: ApplicationDao
): LoggerRepository {

    override suspend fun insertTransaction(info: TransactionInfo, items: List<Items>) {
        val transactionId = dao.insertTransactionInfo(info)

        Log.d("Items", transactionId.toString())
        items.forEach { item -> item.transactionId = transactionId }

        Log.d("Items", items.toString())
        dao.insertItems(items) }

    override suspend fun getTransaction(id: Long): Transaction? {
        return dao.getTransaction(id) }

    override fun getTransInfo(): Flow<List<TransactionInfo>> {
        return dao.getTransactionInfo()
    }

}
