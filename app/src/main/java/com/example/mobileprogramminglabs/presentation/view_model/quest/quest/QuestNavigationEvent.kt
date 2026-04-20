package com.example.mobileprogramminglabs.presentation.view_model.quest.quest

sealed interface QuestNavigationEvent {
    data object Navigate : QuestNavigationEvent
    data object NavigateBack : QuestNavigationEvent
}
