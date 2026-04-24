package com.example.mobileprogramminglabs.presentation.view_model.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.presentation.ui.screens.profile.util.ProfileData
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Init)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _navigationEvent = Channel<ProfileNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading

            val profileData = ProfileData(
                name = "Ajla Korman",
                levelNo = "3",
                levelDescription = "Adventurer",
                profileStats = listOf(
                    InfoRowData("Completed Quests", additionalInfo = "12"),
                    InfoRowData("Current Streak", additionalInfo = "5 days"),
                    InfoRowData("Total XP", additionalInfo = "120")
                ),
                additionalRows = listOf(
                    InfoRowData("Favorite Habit", additionalInfo = "Drink Water"),
                    InfoRowData("Joined", additionalInfo = "March 2026"),
                    InfoRowData("Badges", additionalInfo = "4")
                )
            )

            _uiState.value = ProfileUiState.Success(profileData)
        }
    }

    fun resetUiState() {
        _uiState.value = ProfileUiState.Init
    }
}
