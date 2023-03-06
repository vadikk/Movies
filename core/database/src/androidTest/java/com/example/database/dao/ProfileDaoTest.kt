package com.example.database.dao

import com.example.testing.KoinTestRule
import com.example.database.MoviesDatabase
import com.example.database.databaseModule
import com.example.database.model.ProfileEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileDaoTest {

    @get:Rule
    val koinTestRule = KoinTestRule(listOf(databaseModule))

    private val profileDao by inject<ProfileDao>(ProfileDao::class.java)
    private val db by inject<MoviesDatabase>(MoviesDatabase::class.java, qualifier = named("Test"))

    @After
    fun close(){
        db.close()
    }

    @Test
    fun insertProfile() = runTest{
        val profile = ProfileEntity(id = 1, "John", "Wick")
        profileDao.insert(profile)

        val savedProfileEntity = profileDao.profile().first()

        assertThat(profile).isEqualTo(savedProfileEntity)

    }
}