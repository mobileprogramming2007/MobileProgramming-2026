package com.example.mobileprogramminglabs.presentation.view_model.achievement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.model.repository.achievement.AchievementRepository
import com.example.mobileprogramminglabs.model.repository.achievement.mapper.toAchievementData
import com.example.mobileprogramminglabs.model.repository.user.SessionRepository
import com.example.mobileprogramminglabs.presentation.ui.screens.achievement.util.AchievementData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor(
    private val achievementRepository: AchievementRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<AchievementUiState>(AchievementUiState.Init)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent =
        Channel<AchievementNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        loadAchievements()
    }

    fun loadAchievements() {
        viewModelScope.launch {
            _uiState.value = AchievementUiState.Loading
            try {
                val userId = sessionRepository.observeLoggedInUserId().first()

                if (userId == null) {
                    _uiState.value = AchievementUiState.Error("No logged in user.")
                    return@launch
                }

                val achievements = achievementRepository
                    .getUnlockedAchievementsForUser(userId)
                    .map { achievement ->
                        AchievementData(
                            id = achievement.id,
                            title = achievement.title,
                            description = achievement.description,
                            xpBonus = achievement.xpBonus,
                            conditionType = achievement.conditionType,
                            isUnlocked = true,
                            unlockedAt = null,
                            iconRes = R.drawable.achievement
                        )
                    }

                _uiState.value = AchievementUiState.Success(achievements)
            } catch (e: Exception) {
                _uiState.value = AchievementUiState.Error(
                    e.message ?: "Failed to load achievements."
                )
            }
        }
    }

    fun resetUiState() {
        loadAchievements()
    }
}
