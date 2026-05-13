package com.example.mobileprogramminglabs.presentation.view_model.habit.edit_habit

sealed interface EditHabitNavigationEvent {
    data object NavigateBack : EditHabitNavigationEvent
}