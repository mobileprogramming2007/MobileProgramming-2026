package com.example.mobileprogramminglabs.presentation.ui.screens.achievement.util

import androidx.compose.material3.Icon
import com.example.mobileprogramminglabs.R

data class AchievementData(
    val id: Int,
    val title: String,
    val description: String,
    val xpBonus: Int,
    val conditionType: String,
    val isUnlocked: Boolean,
    val unlockedAt: String? = null,
    val iconRes: Int = R.drawable.achievement
)