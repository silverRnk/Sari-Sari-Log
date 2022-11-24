package com.example.sari_saristorelog.repository

import com.example.sari_saristorelog.data.repository.ApplicationDao
import com.example.sari_saristorelog.data.transaction.Items
import com.example.sari_saristorelog.data.transaction.Transaction
import com.example.sari_saristorelog.data.transaction.TransactionInfo

class LoggerRepositoryImp(
    private val dao: ApplicationDao
): LoggerRepository {

    override suspend fun insertTransaction(info: TransactionInfo, items: List<Items>) {
        val transactionId = dao.insertTransactionInfo(info)

        items.forEach { item -> item.transactionId = transactionId }

        dao.insertItems(items) }

    override suspend fun getTransaction(id: Long): Transaction? {
        return dao.getTransaction(id) }
}