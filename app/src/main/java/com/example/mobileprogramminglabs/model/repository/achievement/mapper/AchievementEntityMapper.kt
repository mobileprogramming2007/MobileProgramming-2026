package com.example.mobileprogramminglabs.model.repository.achievement.mapper

import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.model.data.local.entity.AchievementEntity
import com.example.mobileprogramminglabs.model.data.local.entity.UserAchievementEntity
import com.example.mobileprogramminglabs.presentation.ui.screens.achievement.util.AchievementData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Pair<AchievementEntity, UserAchievementEntity?>.toAchievementData(): AchievementData {
    val achievement = first
    val userAchievement = second

    return AchievementData(
        id = achievement.id,
        title = achievement.title,
        description = achievement.description,
        xpBonus = achievement.xpBonus,
        conditionType = achievement.conditionType,
        isUnlocked = userAchievement != null,
        unlockedAt = userAchievement?.unlockedAt?.let {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(it))
        },
        iconRes = R.drawable.achievement
    )
}
