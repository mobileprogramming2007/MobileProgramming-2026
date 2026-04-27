package com.example.mobileprogramminglabs.presentation.view_model.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.model.repository.FakeProfileRepository
import com.example.mobileprogramminglabs.presentation.ui.screens.profile.util.ProfileData
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Init)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _navigationEvent = Channel<ProfileNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    private val repository = FakeProfileRepository()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading

            /*try {
                val profileData = repository.getProfile(shouldFail = true)
                _uiState.value = ProfileUiState.Success(profileData)
            } catch (e: CancellationException) {
                throw e
            } catch (e: IllegalStateException) {
                _uiState.value = ProfileUiState.Error(
                    e.message ?: "Failed to load profile."
                )
            }*/

            try {
                val statsDeferred = async { repository.getProfileStats() }
                val rowsDeferred = async { repository.getAdditionalRows() }

                val stats = statsDeferred.await()
                val rows = rowsDeferred.await()

                val profileData = ProfileData(
                    name = "Ajla Korman",
                    levelNo = "3",
                    levelDescription = "Adventurer",
                    profileStats = stats,
                    additionalRows = rows
                )

                _uiState.value = ProfileUiState.Success(profileData)
            } catch (e: CancellationException) {
                throw e
            } catch (e: IllegalStateException) {
                _uiState.value = ProfileUiState.Error(
                    e.message ?: "Failed to load profile."
                )
            }

        }
    }

    fun resetUiState() {
        _uiState.value = ProfileUiState.Init
    }
}
