package com.example.mobileprogramminglabs.presentation.view_model.quest.add_quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddQuestViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<AddQuestUiState>(AddQuestUiState.Init)
    val uiState: StateFlow<AddQuestUiState> = _uiState.asStateFlow()

    private val _navigationEvent = Channel<AddQuestNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun addQuest(
        questTitle: String,
        xpReward: String,
        category: String,
        difficulty: String,
        isDaily: Boolean
    ) {
        viewModelScope.launch {
            _uiState.value = AddQuestUiState.Loading

            val xpRewardInt = xpReward.toIntOrNull()

            when {
                questTitle.isBlank() -> {
                    _uiState.value = AddQuestUiState.Error("Quest title cannot be empty.")
                }

                xpReward.isBlank() -> {
                    _uiState.value = AddQuestUiState.Error("XP reward cannot be empty.")
                }

                xpRewardInt == null -> {
                    _uiState.value = AddQuestUiState.Error("XP reward must be a valid number.")
                }

                category.isBlank() -> {
                    _uiState.value = AddQuestUiState.Error("Please select a category.")
                }

                difficulty.isBlank() -> {
                    _uiState.value = AddQuestUiState.Error("Please select a difficulty.")
                }

                else -> {
                    // for now we only simulate a successful save
                    // later this is where backend/database save will happen

                    _uiState.value = AddQuestUiState.Success
                    _navigationEvent.send(AddQuestNavigationEvent.NavigateBack)
                }
            }
        }
    }

    fun resetUiState() {
        _uiState.value = AddQuestUiState.Init
    }
}
