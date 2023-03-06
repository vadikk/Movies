package com.example.profile.presentation

import com.example.testing.MainDispatcherRule
import com.example.profile.data.repository.FakeProfileRepository
import com.example.profile.domain.useCase.ValidateField
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileVMTest{

    @get:Rule
    val dispatcher = MainDispatcherRule()

    private val validateField = ValidateField()
    private lateinit var profileRepository: FakeProfileRepository
    private lateinit var profileVM: ProfileVM

    @Before
    fun setup(){
        profileRepository = FakeProfileRepository()
        profileVM = ProfileVM(validateField, profileRepository)
    }

    @Test
    fun applyBtnEnabledWhenChangeFirstName() = runTest {
        val collectJob = launch { profileVM.profileState.collect() }

        profileVM.changeFirstName("John")
        val item = profileVM.profileState.value

        assertThat(item.isApplyBtnEnabled).isTrue()

        collectJob.cancel()
    }

    @Test
    fun applyBtnEnabledWhenChangeLastName() = runTest {
        val collectJob = launch { profileVM.profileState.collect() }

        profileVM.changeLastName("Wick")
        val item = profileVM.profileState.value

        assertThat(item.isApplyBtnEnabled).isTrue()

        collectJob.cancel()
    }

    @Test
    fun checkApplyBtnEnabledState() = runTest {
        val collectJob = launch { profileVM.profileState.collect() }

        profileVM.changeFirstName("John")
        profileVM.changeLastName("Wick")

        assertThat(profileVM.profileState.value.isApplyBtnEnabled).isTrue()

        profileVM.changeLastName("")
        assertThat(profileVM.profileState.value.isApplyBtnEnabled).isTrue()

        profileVM.changeFirstName("")
        assertThat(profileVM.profileState.value.isApplyBtnEnabled).isFalse()

        collectJob.cancel()
    }

    @Test
    fun saveChanges() = runTest {
        val collectJob = launch { profileVM.profileState.collect() }

        profileVM.changeFirstName("Tony")
        profileVM.changeLastName("Stark")
        profileVM.saveChanges()

        advanceUntilIdle()
        val savedProfile = profileRepository.profile().first()

        assertThat(profileVM.profileState.value.profile).isEqualTo(savedProfile)

        collectJob.cancel()
    }
}