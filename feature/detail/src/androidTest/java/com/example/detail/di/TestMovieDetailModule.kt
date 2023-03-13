package com.example.detail.di

import com.example.detail.repository.TestMovieDetailRepository
import com.example.movie.di.DataMovieModule
import com.example.movie.repository.movieDetail.MovieDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [DataMovieModule::class],
)
interface TestMovieDetailModule {

    @Binds
    fun movieDetailRepository(
        movieDetailRepositoryImpl: TestMovieDetailRepository
    ): MovieDetailRepository
}