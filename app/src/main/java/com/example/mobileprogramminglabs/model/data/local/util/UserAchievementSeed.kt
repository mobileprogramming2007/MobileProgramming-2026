package com.example.mobileprogramminglabs.model.data.local.util

import com.example.mobileprogramminglabs.model.data.local.entity.UserAchievementEntity

object UserAchievementSeed {

    fun defaultUserAchievements(userId: Int): List<UserAchievementEntity> {
        return listOf(
            UserAchievementEntity(
                userId = userId,
                achievementId = 1,
                unlockedAt = System.currentTimeMillis()
            ),
            UserAchievementEntity(
                userId = userId,
                achievementId = 2,
                unlockedAt = System.currentTimeMillis()
            )
        )
    }
}
