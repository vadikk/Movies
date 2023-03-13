package com.example.profile.di

import com.example.profile.domain.useCase.ValidateField
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    internal fun profileRepository(): ValidateField = ValidateField()
}