package com.example.movie

import com.example.common.DispatcherQualifier
import com.example.common.dispatcherModule
import com.example.database.databaseModule
import com.example.movie.repository.movie.MovieRepository
import com.example.movie.repository.movie.MovieRepositoryImpl
import com.example.movie.repository.movieDetail.MovieDetailRepository
import com.example.movie.repository.movieDetail.MovieDetailRepositoryImpl
import com.example.network.networkModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataMovieModule = module {
    includes(networkModule, databaseModule)
    factory<MovieRepository> {
        MovieRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get(qualifier = named(DispatcherQualifier.IO))
        )
    }
    factory<MovieDetailRepository> {
        MovieDetailRepositoryImpl(get(), get())
    }
}


val dataMovieTestModule = module {
    includes(networkModule, databaseModule, dispatcherModule)
    factory<MovieRepository> {
        MovieRepositoryImpl(
            get(qualifier = named("Test")),
            get(),
            get(),
            get(),
            get(qualifier = named(DispatcherQualifier.IO))
        )
    }
}