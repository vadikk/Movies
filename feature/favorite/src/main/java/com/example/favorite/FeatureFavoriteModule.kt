package com.example.favorite

import com.example.movie.dataMovieModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureFavoriteModule = module {
    includes(dataMovieModule)
    viewModel { FavoriteListVM(get()) }
}