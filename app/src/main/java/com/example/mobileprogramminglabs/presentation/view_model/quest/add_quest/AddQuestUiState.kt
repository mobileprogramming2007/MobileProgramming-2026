package com.example.mobileprogramminglabs.presentation.view_model.quest.add_quest

sealed interface AddQuestUiState {
    data object Init : AddQuestUiState
    data object Loading : AddQuestUiState
    data object Success : AddQuestUiState
    data class Error(val message: String) : AddQuestUiState
}
