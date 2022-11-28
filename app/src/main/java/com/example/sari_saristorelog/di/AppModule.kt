package com.example.sari_saristorelog.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sari_saristorelog.data.repository.ApplicationDao
import com.example.sari_saristorelog.data.repository.ApplicationDataBase
import com.example.sari_saristorelog.repository.LoggerRepository
import com.example.sari_saristorelog.repository.LoggerRepositoryImp
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
    fun providesDataBase(app: Application): ApplicationDataBase{
        return Room.databaseBuilder(app,
            ApplicationDataBase::class.java,
        "App Db")
            .build()
    }

    @Provides
    @Singleton
    fun providesDao(db: ApplicationDataBase): ApplicationDao{
        return db.dao()
    }

    @Provides
    @Singleton
    fun provideLoggerRepository(dao: ApplicationDao): LoggerRepository{
        return LoggerRepositoryImp(dao) as LoggerRepository
    }
}