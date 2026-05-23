package com.example.mobileprogramminglabs.presentation.view_model.auth.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.domain.data.RegisterUserData
import com.example.mobileprogramminglabs.domain.repository.AuthRepository
import com.example.mobileprogramminglabs.domain.repository.SessionRepository
import com.example.mobileprogramminglabs.domain.repository.UserRepository
import com.example.mobileprogramminglabs.presentation.util.GoogleAuthClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val authRepository: AuthRepository,
    private val googleAuthClient: GoogleAuthClient
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Init)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _navigationEvent = Channel<LoginNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
                val user = userRepository.getUserByEmailAndPassword(email, password)
                if (user != null) {
                    sessionRepository.saveLoggedInUserId(user.id)

                    val savedUserId = sessionRepository.observeLoggedInUserId().first()
                    android.util.Log.d("SESSION_TEST", "Saved userId: $savedUserId")

                    _uiState.value = LoginUiState.Success(isLoggedIn = true)
                    _navigationEvent.send(LoginNavigationEvent.Navigate(user.id))
                } else {
                    _uiState.value = LoginUiState.Error("Invalid email or password.")
                }
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(
                    e.message ?: "Login failed. Please try again."
                )
            }
        }
    }

    fun onGoogleLoginClick(context: Context) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            try {
                val idToken = googleAuthClient.getGoogleIdToken(context)

                val firebaseUser = authRepository.signInWithGoogle(idToken)

                if (firebaseUser == null || firebaseUser.email.isNullOrBlank()) {
                    _uiState.value = LoginUiState.Error("Google sign-in failed.")
                    return@launch
                }

                val email = firebaseUser.email.orEmpty()
                val fullName = firebaseUser.displayName ?: "Google User"

                var localUser = userRepository.getUserByEmail(email)

                if (localUser == null) {
                    userRepository.insertUser(
                        RegisterUserData(
                            fullName = fullName,
                            email = email,
                            password = "GOOGLE_SIGN_IN"
                        )
                    )

                    localUser = userRepository.getUserByEmail(email)
                }

                if (localUser != null) {
                    sessionRepository.saveLoggedInUserId(localUser.id)

                    _uiState.value = LoginUiState.Success(isLoggedIn = true)
                    _navigationEvent.send(LoginNavigationEvent.Navigate(localUser.id))
                } else {
                    _uiState.value = LoginUiState.Error("Failed to create local user.")
                }
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(
                    e.message ?: "Google login failed. Please try again."
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Init
    }
}
