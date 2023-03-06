package com.example.profile.data.repository

import com.example.profile.domain.model.Profile
import com.example.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

internal class FakeProfileRepository: ProfileRepository {

    private var profile: Profile? = null

    override suspend fun insert(profile: Profile?) {
        this.profile = profile
    }

    override suspend fun getProfile(): Profile? = profile

    override fun profile(): Flow<Profile?> = flow {
        emit(profile)
    }
}