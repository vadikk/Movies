package com.example.popular

import com.example.movie.dataMovieTestModule
import com.example.popular.presentation.PopularListVM
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
val testPopularModule = module {
    includes(dataMovieTestModule)
    viewModel { PopularListVM(get(), StandardTestDispatcher()) }
}