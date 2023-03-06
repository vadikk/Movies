package com.example.database

import androidx.room.Room
import com.example.database.utils.TransactionProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room
            .databaseBuilder(get(), MoviesDatabase::class.java, "movies_database")
            .build()
    }

    single(qualifier = named("Test")) {
        Room.inMemoryDatabaseBuilder(
            get(),
            MoviesDatabase::class.java
        ).build()
    }

    single { get<MoviesDatabase>().moviesDao() }

    single { get<MoviesDatabase>().movieRemoteKeysDao() }

    single { get<MoviesDatabase>().movieFavoriteDao() }

    single { get<MoviesDatabase>().profileDao() }

    factory { TransactionProvider(get()) }
}