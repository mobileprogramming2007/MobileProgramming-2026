package com.example.mobileprogramminglabs.presentation.view_model.habit.add_habit

sealed interface AddHabitNavigationEvent {
    data object Navigate : AddHabitNavigationEvent
    data object NavigateBack : AddHabitNavigationEvent
}