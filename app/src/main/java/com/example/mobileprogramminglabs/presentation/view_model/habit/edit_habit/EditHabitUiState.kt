package com.example.mobileprogramminglabs.presentation.view_model.habit.edit_habit

sealed interface EditHabitUiState {
    data object Init : EditHabitUiState
    data object Loading : EditHabitUiState
    data class Success(
        val title: String,
        val description: String,
        val frequency: String,
        val completed: Boolean
    ) : EditHabitUiState
    data class Error(val message: String) : EditHabitUiState
}