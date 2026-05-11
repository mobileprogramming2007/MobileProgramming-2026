package com.example.mobileprogramminglabs.presentation.view_model.habit.add_habit

sealed interface AddHabitUiState {
    data object Init : AddHabitUiState
    data object Loading : AddHabitUiState
    data class Error(val message: String) : AddHabitUiState
    data object Success : AddHabitUiState
}