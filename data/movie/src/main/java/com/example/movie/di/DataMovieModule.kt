package com.example.movie.di

import com.example.common.di.IoDispatcher
import com.example.database.dao.MovieFavoriteDao
import com.example.database.dao.MovieRemoteKeysDao
import com.example.database.dao.MoviesDao
import com.example.movie.repository.movie.MovieRepository
import com.example.movie.repository.movie.MovieRepositoryImpl
import com.example.movie.repository.movieDetail.MovieDetailRepository
import com.example.movie.repository.movieDetail.MovieDetailRepositoryImpl
import com.example.network.NetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(ViewModelComponent::class)
interface DataMovieModule {

    @Binds
    fun movieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    fun movieDetailRepository(
        movieDetailRepositoryImpl: MovieDetailRepositoryImpl
    ): MovieDetailRepository
}