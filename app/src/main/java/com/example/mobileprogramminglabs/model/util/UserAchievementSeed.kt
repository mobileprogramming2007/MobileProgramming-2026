package com.example.mobileprogramminglabs.model.util

import com.example.mobileprogramminglabs.model.datasource.local.entity.UserAchievementEntity

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
