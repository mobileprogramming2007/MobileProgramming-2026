package com.example.mobileprogramminglabs.presentation.view_model.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.model.repository.user.SessionRepository
import com.example.mobileprogramminglabs.model.repository.user.mapper.toProfileData
import com.example.mobileprogramminglabs.model.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userId: Int = checkNotNull(savedStateHandle["userId"])

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Init)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _navigationEvent = Channel<ProfileNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        observeProfile()
    }
    private fun observeProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading

            userRepository.observeUserById(userId)
                .catch { e ->
                    _uiState.value = ProfileUiState.Error(e.message ?: "Failed to load profile.")
                }
                .collect { user ->
                    if (user == null) {
                        _uiState.value = ProfileUiState.Error("Profile not found.")
                    } else {
                        _uiState.value = ProfileUiState.Success(user.toProfileData())
                    }
                }
        }
    }

    fun onEditClick() {
        viewModelScope.launch {
            _navigationEvent.send(ProfileNavigationEvent.Navigate)
        }
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            sessionRepository.clearSession()
            val savedUserId = sessionRepository.observeLoggedInUserId().first()
            android.util.Log.d("SESSION_TEST", "After logout userId: $savedUserId")
            _navigationEvent.send(ProfileNavigationEvent.NavigateBack)
        }
    }

    fun onDeleteClick() {
        viewModelScope.launch {
            try {
                val user = userRepository.getUserById(userId) ?: return@launch
                userRepository.deleteUser(user)
                sessionRepository.clearSession()
                _navigationEvent.send(ProfileNavigationEvent.NavigateBack)
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error(
                    e.message ?: "Failed to delete profile."
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = ProfileUiState.Init
    }
}
