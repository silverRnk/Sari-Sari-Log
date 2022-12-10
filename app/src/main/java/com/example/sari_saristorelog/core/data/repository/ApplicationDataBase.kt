package com.example.sari_saristorelog.core.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sari_saristorelog.feature_transaction_log.domain.model.deprecated.Customer
import com.example.sari_saristorelog.feature_transaction_log.domain.model.Items
import com.example.sari_saristorelog.feature_transaction_log.domain.model.TransactionInfo

@Database(entities = [TransactionInfo::class,
                     Items::class],
    version = 4
)
@TypeConverters(ApplicationDBTypeConverters::class)
abstract class ApplicationDataBase: RoomDatabase() {
    abstract fun dao(): ApplicationDao
}