package com.example.profile.data.repository

import com.example.database.dao.ProfileDao
import com.example.profile.data.toDatabaseModel
import com.example.profile.data.toDomainModel
import com.example.profile.domain.model.Profile
import com.example.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ProfileRepositoryImpl(
    private val profileDao: ProfileDao
): ProfileRepository {

    override suspend fun insert(profile: Profile?) {
        profileDao.insert(profile?.toDatabaseModel())
    }

    override suspend fun getProfile(): Profile? = profileDao.getProfile()?.toDomainModel()

    override fun profile(): Flow<Profile?> = profileDao.profile().map { it?.toDomainModel() }
}