package com.example.database.di

import android.app.Application
import androidx.room.Room
import com.example.database.MoviesDatabase
import com.example.database.utils.TransactionProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {

    @Provides
    @Singleton
    fun getDBInstance(application: Application): MoviesDatabase {
        val roomBuilder = Room
            .inMemoryDatabaseBuilder(application, MoviesDatabase::class.java)
        return roomBuilder.build()
    }

    @Provides
    fun getTransactionProvider(
        database: MoviesDatabase
    ): TransactionProvider = TransactionProvider(database)
}