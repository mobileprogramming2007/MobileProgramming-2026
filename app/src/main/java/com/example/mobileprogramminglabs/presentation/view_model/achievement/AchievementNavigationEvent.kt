package com.example.mobileprogramminglabs.presentation.view_model.achievement

sealed interface AchievementNavigationEvent {
    data object Navigate : AchievementNavigationEvent
    data object NavigateBack : AchievementNavigationEvent
}