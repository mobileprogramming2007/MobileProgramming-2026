package com.example.mobileprogramminglabs.presentation.view_model.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.model.mapper.toHabitModel
import com.example.mobileprogramminglabs.domain.repository.HabitRepository
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
class HabitViewModel @Inject constructor(
    private val habitRepository: HabitRepository
) : ViewModel() {

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
                val habits = habitRepository.getHabits().map { it.toHabitModel() }
                _uiState.value = HabitUiState.Success(habits)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = HabitUiState.Error(
                    e.message ?: "Failed to load habits."
                )
            }
        }
    }

    fun deleteHabit(habitId: Int) {
        viewModelScope.launch {
            try {
                habitRepository.deleteHabit(habitId)
                loadHabits()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = HabitUiState.Error(
                    e.message ?: "Failed to delete habit."
                )
            }
        }
    }

    fun resetUiState() {
        loadHabits()
    }
}