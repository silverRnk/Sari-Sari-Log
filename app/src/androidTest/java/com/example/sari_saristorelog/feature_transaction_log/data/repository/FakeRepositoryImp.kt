package com.example.sari_saristorelog.feature_transaction_log.data.repository

import com.example.sari_saristorelog.Util.Transactions
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfoAndCustomer
import com.example.sari_saristorelog.feature_transaction_log.domain.repository.LoggerRepository
import com.example.sari_saristorelog.feature_transaction_log.domain.util.QueryOrder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeRepositoryImp: LoggerRepository {

    var transactioninfo1 = TransactionInfo(transactionId = 1, customerName = "Pat", customerIcon = 1, createdDate = 1, total = 8.00)
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


    override suspend fun insertTransaction(info: TransactionInfo, items: List<Items>) {
        TODO("Not yet implemented")
    }

    override suspend fun getTransaction(id: Long): Transaction? {
        return Transaction(transactionInfo = transactioninfo1, items = listOf())
    }

    override fun getTransInfo(order: QueryOrder): Flow<List<TransactionInfo>> {

        val flowTransactionInfo = flowOf(listOf(Transactions.transactioninfo1, Transactions.transaction2, Transactions.transaction3))
        return flowTransactionInfo
    }

}