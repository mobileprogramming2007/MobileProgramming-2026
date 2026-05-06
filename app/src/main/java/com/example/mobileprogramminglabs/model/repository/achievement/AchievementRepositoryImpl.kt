package com.example.mobileprogramminglabs.model.repository.achievement

import com.example.mobileprogramminglabs.model.data.local.dao.AchievementDao
import com.example.mobileprogramminglabs.model.data.local.entity.AchievementEntity
import com.example.mobileprogramminglabs.model.data.local.entity.UserAchievementEntity
import com.example.mobileprogramminglabs.model.data.local.util.AchievementSeed
import com.example.mobileprogramminglabs.model.data.local.util.UserAchievementSeed
import javax.inject.Inject

class AchievementRepositoryImpl @Inject constructor(
    private val achievementDao: AchievementDao
) : AchievementRepository {

    override suspend fun seedAchievementsIfNeeded() {
        val achievements = achievementDao.getAllAchievements()
        if (achievements.isEmpty()) {
            achievementDao.insertAchievements(AchievementSeed.defaultAchievements)
        }
    }

    override suspend fun getAllAchievements(): List<AchievementEntity> {
        return achievementDao.getAllAchievements()
    }

    override suspend fun getUserAchievements(userId: Int): List<UserAchievementEntity> {
        return achievementDao.getUserAchievements(userId)
    }

    override suspend fun isAchievementUnlocked(
        userId: Int,
        achievementId: Int
    ): Boolean {
        return achievementDao.isAchievementUnlocked(userId, achievementId)
    }

    override suspend fun unlockAchievement(
        userId: Int,
        achievementId: Int
    ) {
        val alreadyUnlocked = achievementDao.isAchievementUnlocked(
            userId = userId,
            achievementId = achievementId
        )

        if (!alreadyUnlocked) {
            achievementDao.insertUserAchievement(
                UserAchievementEntity(
                    userId = userId,
                    achievementId = achievementId,
                    unlockedAt = System.currentTimeMillis()
                )
            )
        }
    }

    override suspend fun getAchievementsForUser(
        userId: Int
    ): List<Pair<AchievementEntity, UserAchievementEntity?>> {
        seedAchievementsIfNeeded()

        val achievements = achievementDao.getAllAchievements()
        val userAchievements = achievementDao.getUserAchievements(userId)

        return achievements.map { achievement ->
            val unlockedAchievement = userAchievements.find {
                it.achievementId == achievement.id
            }
            achievement to unlockedAchievement
        }
    }

    override suspend fun seedUserAchievementsIfNeeded(userId: Int) {
        val count = achievementDao.getUserAchievementCount(userId)
        if (count == 0) {
            achievementDao.insertUserAchievements(
                UserAchievementSeed.defaultUserAchievements(userId)
            )
        }
    }

    override suspend fun getUnlockedAchievementsForUser(userId: Int): List<AchievementEntity> {
        return achievementDao.getUnlockedAchievementsForUser(userId)
    }
}
