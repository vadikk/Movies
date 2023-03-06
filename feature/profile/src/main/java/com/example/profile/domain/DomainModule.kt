package com.example.profile.domain

import com.example.profile.domain.useCase.ValidateField
import org.koin.dsl.module

internal val domainModule = module {
    factory { ValidateField() }
}