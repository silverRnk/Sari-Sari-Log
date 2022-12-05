package com.example.sari_saristorelog.feature_transaction_log.data.repository

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

        items.forEach { item -> item.transactionId = transactionId }

        dao.insertItems(items) }

    override suspend fun getTransaction(id: Long): Transaction? {
        return dao.getTransaction(id) }


    override suspend fun getTransInfo(order: QueryOrder): Flow<List<TransactionInfo>> {
        return when(order){
            is QueryOrder.Asc -> {
                dao.getTransactionInfo().map {list -> list.sortedBy { it.createdDate } }
            }
            is QueryOrder.Desc -> {
                dao.getTransactionInfo().map {list -> list.sortedByDescending { it.createdDate } }
            }
        }
    }

    override suspend fun findTransInfoWithCustomerByName(name: String, list: List<TransactionInfoAndCustomer>): List<TransactionInfoAndCustomer> {
        return list.filter { it.nickName == name }
    }
}
