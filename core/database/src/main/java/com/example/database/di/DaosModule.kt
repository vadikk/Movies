package com.example.database.di

import com.example.database.MoviesDatabase
import com.example.database.dao.MovieFavoriteDao
import com.example.database.dao.MovieRemoteKeysDao
import com.example.database.dao.MoviesDao
import com.example.database.dao.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesMoviesDao(
        database: MoviesDatabase
    ): MoviesDao = database.moviesDao()

    @Provides
    fun providesMovieRemoteKeysDao(
        database: MoviesDatabase
    ): MovieRemoteKeysDao = database.movieRemoteKeysDao()

    @Provides
    fun providesMovieFavoriteDao(
        database: MoviesDatabase
    ): MovieFavoriteDao = database.movieFavoriteDao()

    @Provides
    fun providesProfileDao(
        database: MoviesDatabase
    ): ProfileDao = database.profileDao()
}