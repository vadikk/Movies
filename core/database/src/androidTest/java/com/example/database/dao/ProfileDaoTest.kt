package com.example.database.dao

import com.example.database.MoviesDatabase
import com.example.database.model.ProfileEntity
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class ProfileDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db: MoviesDatabase

    @Inject
    lateinit var profileDao: ProfileDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun close() {
        db.close()
    }

    @Test
    fun insertProfile() = runTest {
        val profile = ProfileEntity(id = 1, "John", "Wick")
        profileDao.insert(profile)

        val savedProfileEntity = profileDao.profile().first()

        assertThat(profile).isEqualTo(savedProfileEntity)

    }
}