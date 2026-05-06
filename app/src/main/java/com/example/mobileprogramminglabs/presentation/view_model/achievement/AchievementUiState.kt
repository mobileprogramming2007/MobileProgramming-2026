package com.example.mobileprogramminglabs.presentation.view_model.achievement

import com.example.mobileprogramminglabs.presentation.ui.screens.achievement.util.AchievementData

sealed interface AchievementUiState {
    data object Init : AchievementUiState
    data object Loading : AchievementUiState
    data class Success(
        val achievements: List<AchievementData>
    ) : AchievementUiState
    data class Error(val message: String) : AchievementUiState
}