package com.example.mobileprogramminglabs.presentation.view_model.quest.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.presentation.ui.screens.quest.util.QuestData
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class QuestViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<QuestUiState>(QuestUiState.Init)
    val uiState: StateFlow<QuestUiState> = _uiState.asStateFlow()

    private val _navigationEvent = Channel<QuestNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    private val _quests = MutableStateFlow(
        listOf(
            QuestData(1, "Study Kotlin", 20, false),
            QuestData(2, "Workout", 15, true),
            QuestData(3, "Drink Water", 10, false),
            QuestData(4, "Read 10 Pages", 25, false)
        )
    )
    val quests: StateFlow<List<QuestData>> = _quests.asStateFlow()

    fun toggleQuest(questId: Int, isChecked: Boolean) {
        viewModelScope.launch {
            _uiState.value = QuestUiState.Loading

            _quests.value = _quests.value.map { currentQuest ->
                if (currentQuest.id == questId) {
                    currentQuest.copy(isCompleted = isChecked)
                } else {
                    currentQuest
                }
            }

            _uiState.value = QuestUiState.Success
        }
    }

    fun deleteQuest(questId: Int) {
        viewModelScope.launch {
            _uiState.value = QuestUiState.Loading

            _quests.value = _quests.value.filterNot { it.id == questId }

            _uiState.value = QuestUiState.Success
        }
    }


    fun resetUiState() {
        _uiState.value = QuestUiState.Init
    }
}
