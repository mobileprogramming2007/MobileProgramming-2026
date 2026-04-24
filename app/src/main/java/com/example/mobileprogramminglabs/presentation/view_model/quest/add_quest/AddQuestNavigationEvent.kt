package com.example.mobileprogramminglabs.presentation.view_model.quest.add_quest

sealed interface AddQuestNavigationEvent {
    data object Navigate : AddQuestNavigationEvent
    data object NavigateBack : AddQuestNavigationEvent
}
