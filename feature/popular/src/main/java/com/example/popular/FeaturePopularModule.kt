package com.example.popular

import com.example.common.DispatcherQualifier
import com.example.movie.dataMovieModule
import com.example.popular.presentation.PopularListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featurePopularModule = module {
    includes(dataMovieModule)
    viewModel { PopularListVM(get(), get(qualifier = named(DispatcherQualifier.IO))) }
}