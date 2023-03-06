package com.example.profile.data

import com.example.database.model.ProfileEntity
import com.example.profile.domain.model.Profile

internal fun ProfileEntity.toDomainModel(): Profile =
    Profile(id, firstName, lastName)

internal fun Profile.toDatabaseModel(): ProfileEntity =
    ProfileEntity(id = id, firstName = firstName, lastName = lastName)