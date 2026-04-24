package com.example.mobileprogramminglabs.presentation.view_model.habit

import com.example.mobileprogramminglabs.presentation.ui.screens.habit.util.HabitModel

sealed interface HabitUiState {
    data object Init : HabitUiState
    data object Loading : HabitUiState
    data class Success(val habits: List<HabitModel>) : HabitUiState
    data class Error(val message: String) : HabitUiState
}
