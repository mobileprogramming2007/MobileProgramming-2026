package com.example.mobileprogramminglabs.presentation.view_model.profile.edit_profile

sealed interface EditProfileNavigationEvent {
    data object Navigate : EditProfileNavigationEvent
    data object NavigateBack : EditProfileNavigationEvent
}