package com.example.mobileprogramminglabs.presentation.view_model.quest.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.domain.data.QuestData
import com.example.mobileprogramminglabs.domain.repository.QuestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val questRepository: QuestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<QuestUiState>(QuestUiState.Loading)
    val uiState: StateFlow<QuestUiState> = _uiState.asStateFlow()

    private val _quests = MutableStateFlow<List<QuestData>>(emptyList())
    val quests: StateFlow<List<QuestData>> = _quests.asStateFlow()

    fun loadQuests() {
        viewModelScope.launch {
            _uiState.value = QuestUiState.Loading

            try {
                val questList = questRepository.getQuests()
                _quests.value = questList
                _uiState.value = QuestUiState.Success
            } catch (e: Exception) {
                _uiState.value = QuestUiState.Error(
                    e.message ?: "Failed to load quests."
                )
            }
        }
    }

    fun toggleQuest(questId: String, isCompleted: Boolean) {
        viewModelScope.launch {
            try {
                questRepository.toggleQuest(questId, isCompleted)

                _quests.value = _quests.value.map { quest ->
                    if (quest.id == questId) {
                        quest.copy(isCompleted = isCompleted)
                    } else {
                        quest
                    }
                }

                _uiState.value = QuestUiState.Success
            } catch (e: Exception) {
                _uiState.value = QuestUiState.Error(
                    e.message ?: "Failed to update quest."
                )
            }
        }
    }

    fun deleteQuest(questId: String) {
        viewModelScope.launch {
            try {
                questRepository.deleteQuest(questId)

                _quests.value = _quests.value.filter { quest ->
                    quest.id != questId
                }

                _uiState.value = QuestUiState.Success
            } catch (e: Exception) {
                _uiState.value = QuestUiState.Error(
                    e.message ?: "Failed to delete quest."
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = QuestUiState.Success
    }
}
