package com.example.mobileprogramminglabs.presentation.view_model.quest.add_quest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.domain.repository.QuestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddQuestViewModel @Inject constructor(
    private val questRepository: QuestRepository
) : ViewModel() {

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

                xpRewardInt <= 0 -> {
                    _uiState.value = AddQuestUiState.Error("XP reward must be greater than 0.")
                }

                category.isBlank() -> {
                    _uiState.value = AddQuestUiState.Error("Please select a category.")
                }

                difficulty.isBlank() -> {
                    _uiState.value = AddQuestUiState.Error("Please select a difficulty.")
                }

                else -> {
                    try {
                        Log.d(
                            "FIREBASE_TEST",
                            "AddQuestViewModel saving: title=$questTitle, xp=$xpRewardInt, category=$category, difficulty=$difficulty, isDaily=$isDaily"
                        )

                        questRepository.addQuest(
                            title = questTitle.trim(),
                            xp = xpRewardInt,
                            category = category,
                            difficulty = difficulty,
                            isDaily = isDaily
                        )

                        _uiState.value = AddQuestUiState.Success
                        _navigationEvent.send(AddQuestNavigationEvent.NavigateBack)
                    } catch (e: Exception) {
                        Log.e("FIREBASE_TEST", "Failed to save quest", e)
                        _uiState.value = AddQuestUiState.Error(
                            e.message ?: "Failed to save quest."
                        )
                    }
                }
            }
        }
    }

    fun resetUiState() {
        _uiState.value = AddQuestUiState.Init
    }
}
