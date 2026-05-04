package com.example.mobileprogramminglabs.presentation.view_model.profile.edit_profile

sealed interface EditProfileUiState {
    data object Init : EditProfileUiState
    data object Loading : EditProfileUiState
    data object Success : EditProfileUiState
    data class Error(val message: String) : EditProfileUiState
}