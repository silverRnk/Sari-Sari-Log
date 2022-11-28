package com.example.sari_saristorelog.repository

import com.example.sari_saristorelog.data.repository.ApplicationDao
import com.example.sari_saristorelog.data.transaction.Items
import com.example.sari_saristorelog.data.transaction.Transaction
import com.example.sari_saristorelog.data.transaction.TransactionInfo
import com.example.sari_saristorelog.data.transaction.TransactionInfoAndCustomer
import com.example.sari_saristorelog.util.QueryOrder
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

    override suspend fun getTransInfoWithCustomer(order: QueryOrder): List<TransactionInfoAndCustomer> {
        return when(order){
            is QueryOrder.Asc -> {
                dao.getTransWithCustomerAsc()
            }
            is QueryOrder.Desc -> {
                dao.getTransWithCustomerDesc()
            }
            is QueryOrder.FromToDateAsc -> {
                dao.getTransWithCustomerBtwDateAsc(order.fromDate, order.toDate)
            }
            is QueryOrder.FromToDateDesc -> {
                dao.getTransWithCustomerBtwDateDesc(order.fromDate, order.toDate)
            }
        }
    }

    override suspend fun findTransInfoWithCustomerByName(name: String, list: List<TransactionInfoAndCustomer>): List<TransactionInfoAndCustomer> {
        return list.filter { it.nickName == name }
    }
}