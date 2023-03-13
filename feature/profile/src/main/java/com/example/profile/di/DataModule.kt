package com.example.profile.di

import com.example.database.dao.ProfileDao
import com.example.profile.data.repository.ProfileRepositoryImpl
import com.example.profile.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    internal fun profileRepository(
        profileDao: ProfileDao
    ): ProfileRepository = ProfileRepositoryImpl(profileDao)
}