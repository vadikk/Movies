package com.example.profile.presentation

import com.example.profile.data.dataModule
import com.example.profile.domain.domainModule
import org.koin.dsl.module

val featureProfileModule = module {
    includes(dataModule, domainModule)
    factory { ProfileVM(get(), get()) }
}