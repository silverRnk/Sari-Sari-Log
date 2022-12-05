package com.example.sari_saristorelog.core.data.repository

import androidx.room.*
import com.example.sari_saristorelog.feature_transaction_log.domain.model.deprecated.Customer
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Transaction
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo
import kotlinx.coroutines.flow.Flow


@Dao
interface ApplicationDao {


    @Insert(entity = TransactionInfo::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactionInfo(info: TransactionInfo):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<Items>)

    @Delete
    suspend fun deleteTransactionInfo(info: TransactionInfo)

    @Delete
    suspend fun deleteItems(items: List<Items>)


    @Query("SELECT * FROM TransactionInfo")
    suspend fun getTransactionInfoList(): List<TransactionInfo>

    @androidx.room.Transaction
    @Query("SELECT * FROM TransactionInfo WHERE transactionId = :id")
    suspend fun getTransaction(id: Long?): Transaction?

    @Query("SELECT * FROM TransactionInfo")
    fun getTransactionInfo(): Flow<List<TransactionInfo>>

/*    @androidx.room.Transaction
    @Query("SELECT * FROM TransactionInfo WHERE transactionId = :transactionId")
    suspend fun getTransactionWithCustomerById(transactionId: Long): CustomerWithTransaction?*/


}