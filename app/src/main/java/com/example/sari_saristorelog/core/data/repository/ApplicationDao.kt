package com.example.sari_saristorelog.core.data.repository

import androidx.room.*
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Customer
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import com.example.sari_saristorelog.data.transaction.TransactionInfoAndCustomer
import com.example.sari_saristorelog.util.QueryKeys


@Dao
interface ApplicationDao {


    @Insert(entity = TransactionInfo::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactionInfo(info: TransactionInfo):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<Items>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer)

    @Delete
    suspend fun deleteTransactionInfo(info: TransactionInfo)

    @Delete
    suspend fun deleteItems(items: List<Items>)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Query("SELECT * FROM TransactionInfo")
    suspend fun getTransactionInfoList(): List<TransactionInfo>

    @androidx.room.Transaction
    @Query("SELECT * FROM TransactionInfo WHERE transactionId = :id")
    suspend fun getTransaction(id: Long?): Transaction?

    @Query("SELECT * FROM Customer WHERE customerId = :customerId")
    suspend fun getCustomer(customerId: Long?): Customer?

    @Query(QueryKeys.transInfoWithCustomerDesc1 + " ORDER BY createdDate DESC")
    suspend fun getTransWithCustomerDesc(): List<TransactionInfoAndCustomer>

    @Query(QueryKeys.transInfoWithCustomerDesc1 + " ORDER BY createdDate ASC")
    suspend fun getTransWithCustomerAsc(): List<TransactionInfoAndCustomer>

    @Query(QueryKeys.transInfoWithCustomerDesc1 + " WHERE createdDate BETWEEN :fromDate AND :toDate ORDER BY createdDate DESC")
    suspend fun getTransWithCustomerBtwDateDesc(fromDate: Long = 0, toDate: Long = Long.MAX_VALUE): List<TransactionInfoAndCustomer>

    @Query(QueryKeys.transInfoWithCustomerDesc1 + " WHERE createdDate BETWEEN :fromDate AND :toDate ORDER BY createdDate ASC")
    suspend fun getTransWithCustomerBtwDateAsc(fromDate: Long = 0, toDate: Long = Long.MAX_VALUE): List<TransactionInfoAndCustomer>
/*    @androidx.room.Transaction
    @Query("SELECT * FROM TransactionInfo WHERE transactionId = :transactionId")
    suspend fun getTransactionWithCustomerById(transactionId: Long): CustomerWithTransaction?*/


}