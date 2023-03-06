package com.example.common

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    single(qualifier = named(DispatcherQualifier.IO)) { Dispatchers.IO }
    single(qualifier = named(DispatcherQualifier.MAIN)) { Dispatchers.Main }
}

enum class DispatcherQualifier{
    IO, MAIN
}