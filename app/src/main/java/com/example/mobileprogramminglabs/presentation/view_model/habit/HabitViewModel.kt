package com.example.mobileprogramminglabs.presentation.view_model.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.util.HabitModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HabitViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<HabitUiState>(HabitUiState.Init)
    val uiState: StateFlow<HabitUiState> = _uiState.asStateFlow()

    private val _navigationEvent = Channel<HabitNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        loadHabits()
    }

    fun loadHabits() {
        viewModelScope.launch {
            _uiState.value = HabitUiState.Loading

            val habits = listOf(
                HabitModel(title = "Drink Water", streak = "5 days"),
                HabitModel(title = "Read 10 Pages", streak = "3 days"),
                HabitModel(title = "Sleep 8 Hours", streak = "7 days"),
                HabitModel(title = "Morning Walk", streak = "4 days"),
                HabitModel(title = "Meditate", streak = "6 days"),
                HabitModel(title = "Write Journal", streak = "2 days"),
                HabitModel(title = "No Sugar", streak = "8 days"),
                HabitModel(title = "Study Kotlin", streak = "10 days"),
                HabitModel(title = "Exercise", streak = "9 days"),
                HabitModel(title = "Stretching", streak = "4 days"),
                HabitModel(title = "Practice English", streak = "12 days"),
                HabitModel(title = "Eat Breakfast", streak = "11 days"),
                HabitModel(title = "Limit Screen Time", streak = "3 days"),
                HabitModel(title = "Go to Bed Early", streak = "5 days"),
                HabitModel(title = "Review Notes", streak = "7 days"),
                HabitModel(title = "Clean Room", streak = "2 days"),
                HabitModel(title = "Take Vitamins", streak = "14 days"),
                HabitModel(title = "Daily Planning", streak = "6 days"),
                HabitModel(title = "Learn Compose", streak = "8 days"),
                HabitModel(title = "Evening Walk", streak = "4 days")
            )

            _uiState.value = HabitUiState.Success(habits)
        }
    }

    fun resetUiState() {
        loadHabits()
    }
}
