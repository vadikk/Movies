package com.example.detail.presentation

import com.example.movie.dataMovieModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureDetailModule = module {
    includes(dataMovieModule)
    viewModel { MovieDetailVM(get(), get()) }
}