package com.example.sari_saristorelog.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sari_saristorelog.data.Customer
import com.example.sari_saristorelog.data.transaction.Items
import com.example.sari_saristorelog.data.transaction.Transaction
import com.example.sari_saristorelog.data.transaction.TransactionInfo

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
    suspend fun getCustomer(customerId: Int): Customer?

}