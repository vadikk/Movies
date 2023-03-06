package com.example.profile.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.domain.model.Profile
import com.example.profile.domain.repository.ProfileRepository
import com.example.profile.domain.useCase.DataFieldType
import com.example.profile.domain.useCase.ValidateField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ProfileVM(
    private val validateNoteField: ValidateField,
    private val profileRepository: ProfileRepository
): ViewModel() {

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState = _profileState.asStateFlow()

    private var currentProfile: Profile? = null

    init {
        viewModelScope.launch {
            currentProfile = profileRepository.getProfile()
            _profileState.update {
                it.copy(
                    profile = it.profile.copy(
                        id = currentProfile?.id,
                        firstName = currentProfile?.firstName.orEmpty(),
                        lastName = currentProfile?.lastName.orEmpty()
                    )
                )
            }
        }
    }

    fun changeFirstName(newText: String) {
        _profileState.update {
            it.copy(
                profile = it.profile.copy(firstName = newText),
                isApplyBtnEnabled = validateNoteField.validateInputFields(
                    newText,
                    currentProfile?.firstName.orEmpty(),
                    DataFieldType.FIRST_NAME
                )
            )
        }
    }

    fun changeLastName(newText: String) {
        _profileState.update {
            it.copy(
                profile = it.profile.copy(lastName = newText),
                isApplyBtnEnabled = validateNoteField.validateInputFields(
                    newText,
                    currentProfile?.lastName.orEmpty(),
                    DataFieldType.LAST_NAME
                )
            )
        }
    }

    fun saveChanges() {
        viewModelScope.launch {
            profileRepository.insert(_profileState.value.profile)
        }
        currentProfile = _profileState.value.profile
        _profileState.update {
            it.copy(
                isApplyBtnEnabled = false
            )
        }
    }
}

internal data class ProfileState(
    val profile: Profile = Profile(),
    val isApplyBtnEnabled: Boolean = false
)