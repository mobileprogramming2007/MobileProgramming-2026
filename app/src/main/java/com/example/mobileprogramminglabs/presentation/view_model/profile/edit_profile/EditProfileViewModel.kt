package com.example.mobileprogramminglabs.presentation.view_model.profile.edit_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.model.repository.user.mapper.toEditProfileData
import com.example.mobileprogramminglabs.model.repository.user.mapper.toUserEntity
import com.example.mobileprogramminglabs.model.repository.user.UserRepository
import com.example.mobileprogramminglabs.presentation.ui.screens.profile.util.EditProfileData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<EditProfileUiState>(EditProfileUiState.Init)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = Channel<EditProfileNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    var formState by mutableStateOf(EditProfileData())
        private set

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            _uiState.value = EditProfileUiState.Loading
            try {
                val user = userRepository.getUserById(1)

                if (user == null) {
                    _uiState.value = EditProfileUiState.Error("User not found.")
                    return@launch
                }

                formState = user.toEditProfileData()
                _uiState.value = EditProfileUiState.Success
            } catch (e: Exception) {
                _uiState.value = EditProfileUiState.Error(
                    e.message ?: "Failed to load profile."
                )
            }
        }
    }

    fun onFullNameChange(value: String) {
        formState = formState.copy(fullName = value)
    }

    fun onEmailChange(value: String) {
        formState = formState.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        formState = formState.copy(password = value)
    }

    fun onConfirmPasswordChange(value: String) {
        formState = formState.copy(confirmPassword = value)
    }

    fun onSaveClick() {
        viewModelScope.launch {
            _uiState.value = EditProfileUiState.Loading

            try {
                if (
                    formState.fullName.isBlank() ||
                    formState.email.isBlank() ||
                    formState.password.isBlank() ||
                    formState.confirmPassword.isBlank()
                ) {
                    _uiState.value = EditProfileUiState.Error("All fields are required.")
                    return@launch
                }

                if (formState.password != formState.confirmPassword) {
                    _uiState.value = EditProfileUiState.Error("Passwords do not match.")
                    return@launch
                }

                userRepository.updateUser(formState.toUserEntity())

                _uiState.value = EditProfileUiState.Success
                _navigationEvent.send(EditProfileNavigationEvent.NavigateBack)
            } catch (e: Exception) {
                _uiState.value = EditProfileUiState.Error(
                    e.message ?: "Failed to update profile."
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = EditProfileUiState.Init
    }
}
