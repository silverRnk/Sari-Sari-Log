package com.example.sari_saristorelog.di

import com.example.sari_saristorelog.feature_transaction_log.data.repository.FakeRepositoryImp
import com.example.sari_saristorelog.feature_transaction_log.domain.repository.LoggerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Singleton
    fun provideRepository(): LoggerRepository{
        return FakeRepositoryImp()
    }
}