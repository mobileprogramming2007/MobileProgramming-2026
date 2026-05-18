package com.example.mobileprogramminglabs.domain.repository

import com.example.mobileprogramminglabs.model.datasource.local.entity.AchievementEntity
import com.example.mobileprogramminglabs.model.datasource.local.entity.UserAchievementEntity

interface AchievementRepository {

    suspend fun seedAchievementsIfNeeded()

    suspend fun getAllAchievements(): List<AchievementEntity>

    suspend fun getUserAchievements(userId: Int): List<UserAchievementEntity>

    suspend fun isAchievementUnlocked(
        userId: Int,
        achievementId: Int
    ): Boolean

    suspend fun unlockAchievement(
        userId: Int,
        achievementId: Int
    )

    suspend fun getAchievementsForUser(
        userId: Int
    ): List<Pair<AchievementEntity, UserAchievementEntity?>>

    suspend fun seedUserAchievementsIfNeeded(userId: Int)

    suspend fun getUnlockedAchievementsForUser(userId: Int): List<AchievementEntity>
}
