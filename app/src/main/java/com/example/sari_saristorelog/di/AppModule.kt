package com.example.sari_saristorelog.di

import android.app.Application
import androidx.room.Room
import com.example.sari_saristorelog.core.data.repository.ApplicationDao
import com.example.sari_saristorelog.core.data.repository.ApplicationDataBase
import com.example.sari_saristorelog.feature_transaction_log.data.repository.LoggerRepositoryImp
import com.example.sari_saristorelog.feature_transaction_log.domain.repository.LoggerRepository
import com.example.sari_saristorelog.feature_transaction_log.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesDataBase(app: Application): ApplicationDataBase {
        return Room.databaseBuilder(app,
            ApplicationDataBase::class.java,
        "App Db")
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggerRepository(db: ApplicationDataBase): LoggerRepository {
        return LoggerRepositoryImp(db.dao())
    }

    @Provides
    @Singleton
    fun provideTransactionLogUseCases(repository: LoggerRepository): TransactionLogUseCases{
        return TransactionLogUseCases(
            getTransactionInfoList = GetTransactionInfoList(repository),
            filterByDate = FilterByDate(),
            filterByName = FilterByName(),
            addTransaction = AddTransaction(repository)
        )
    }
}