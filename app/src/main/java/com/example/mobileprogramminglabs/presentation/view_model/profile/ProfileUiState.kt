package com.example.mobileprogramminglabs.presentation.view_model.profile

import com.example.mobileprogramminglabs.presentation.ui.screens.profile.util.ProfileData

sealed interface ProfileUiState {
    data object Init : ProfileUiState
    data object Loading : ProfileUiState
    data class Success(val profileData: ProfileData) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}
