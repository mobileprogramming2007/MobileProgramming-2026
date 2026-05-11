package com.example.mobileprogramminglabs.presentation.view_model.habit.edit_habit

import com.example.mobileprogramminglabs.model.datasource.network.dto.UpdateHabitDto
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.model.repository.habit.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class EditHabitViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val habitId: Int = checkNotNull(savedStateHandle["habitId"])

    private val _uiState = MutableStateFlow<EditHabitUiState>(EditHabitUiState.Init)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = Channel<EditHabitNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        loadHabit()
    }

    fun loadHabit() {
        viewModelScope.launch {
            _uiState.value = EditHabitUiState.Loading
            try {
                val habit = habitRepository.getHabitById(habitId)
                _uiState.value = EditHabitUiState.Success(
                    title = habit.title,
                    description = habit.description,
                    frequency = habit.frequency,
                    completed = habit.completed
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = EditHabitUiState.Error(
                    e.message ?: "Failed to load habit."
                )
            }
        }
    }

    fun updateHabit(
        title: String,
        description: String,
        frequency: String,
        completed: Boolean
    ) {
        viewModelScope.launch {
            _uiState.value = EditHabitUiState.Loading
            try {
                habitRepository.updateHabit(
                    id = habitId,
                    habit = UpdateHabitDto(
                        title = title,
                        description = description,
                        frequency = frequency,
                        completed = completed
                    )
                )
                _navigationEvent.send(EditHabitNavigationEvent.NavigateBack)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = EditHabitUiState.Error(
                    e.message ?: "Failed to update habit."
                )
            }
        }
    }

    fun resetUiState() {
        loadHabit()
    }
}
