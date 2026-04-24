package com.example.mobileprogramminglabs.presentation.view_model.quest.quest

sealed interface QuestUiState {
    data object Init : QuestUiState
    data object Loading : QuestUiState
    data object Success : QuestUiState
    data class Error(val message: String) : QuestUiState
}
