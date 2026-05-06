package com.example.mobileprogramminglabs.presentation.view_model.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.model.repository.FakeHabitRepository
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.util.HabitModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class HabitViewModel @Inject constructor() : ViewModel() {

    private val repository = FakeHabitRepository()

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
            try {
                val habits = repository.getHabits()
                _uiState.value = HabitUiState.Success(habits)
            } catch (e: CancellationException) {
                throw e
            } catch (e: IllegalStateException) {
                _uiState.value = HabitUiState.Error(
                    e.message ?: "Failed to load habits."
                )
            }
        }
    }

    fun resetUiState() {
        loadHabits()
    }
}
