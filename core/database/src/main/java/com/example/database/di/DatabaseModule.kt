package com.example.database.di

import android.app.Application
import androidx.room.Room
import com.example.database.MoviesDatabase
import com.example.database.utils.TransactionProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun getDBInstance(application: Application): MoviesDatabase {
        val roomBuilder = Room
            .databaseBuilder(application, MoviesDatabase::class.java, "movies_database")
        return roomBuilder.build()
    }

    @Provides
    fun getTransactionProvider(
        database: MoviesDatabase
    ): TransactionProvider = TransactionProvider(database)
}