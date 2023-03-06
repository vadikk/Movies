package com.example.profile.data

import com.example.profile.data.repository.ProfileRepositoryImpl
import com.example.profile.domain.repository.ProfileRepository
import org.koin.dsl.module

internal val dataModule = module {
    factory<ProfileRepository> { ProfileRepositoryImpl(get()) }
}