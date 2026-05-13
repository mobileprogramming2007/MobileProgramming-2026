package com.example.mobileprogramminglabs.presentation.view_model.habit.add_habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.model.datasource.network.api.dto.CreateHabitDto
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
class AddHabitViewModel @Inject constructor(
    private val addHabitRepository: HabitRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddHabitUiState>(AddHabitUiState.Init)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = Channel<AddHabitNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun addHabit(
        title: String,
        description: String,
        frequency: String,
        completed: Boolean
    ) {
        viewModelScope.launch {
            _uiState.value = AddHabitUiState.Loading
            try {
                addHabitRepository.createHabit(
                    CreateHabitDto(
                        title = title,
                        description = description,
                        frequency = frequency,
                        completed = completed,
                        user_id = 1
                    )
                )
                _uiState.value = AddHabitUiState.Success
                _navigationEvent.send(AddHabitNavigationEvent.NavigateBack)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = AddHabitUiState.Error(
                    e.message ?: "Failed to save habit."
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = AddHabitUiState.Init
    }
}