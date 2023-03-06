package com.example.detail

import com.example.detail.presentation.MovieDetailVM
import com.example.detail.repository.TestMovieDetailRepository
import com.example.movie.repository.movieDetail.MovieDetailRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testMovieDetailModule = module {
    factory<MovieDetailRepository> {
        TestMovieDetailRepository()
    }
    viewModel { MovieDetailVM(get(), get()) }
}