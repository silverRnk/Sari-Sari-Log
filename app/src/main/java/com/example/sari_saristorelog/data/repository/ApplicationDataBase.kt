package com.example.sari_saristorelog.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sari_saristorelog.data.Customer
import com.example.sari_saristorelog.data.transaction.Items
import com.example.sari_saristorelog.data.transaction.TransactionInfo

@Database(entities = [TransactionInfo::class,
                     Items::class,
                     Customer::class],
    version = 3
)
abstract class ApplicationDataBase: RoomDatabase() {
    abstract fun dao(): ApplicationDao
}