package com.example.profile.domain.repository

import com.example.profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow

internal interface ProfileRepository {
    suspend fun insert(profile: Profile?)
    suspend fun getProfile(): Profile?
    fun profile(): Flow<Profile?>
}