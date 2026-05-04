package com.example.mobileprogramminglabs.model.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobileprogramminglabs.model.data.local.entity.AchievementEntity
import com.example.mobileprogramminglabs.model.data.local.entity.UserAchievementEntity

@Dao
interface AchievementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievements(achievements: List<AchievementEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserAchievement(userAchievement: UserAchievementEntity)

    @Query("SELECT * FROM achievements")
    suspend fun getAllAchievements(): List<AchievementEntity>

    @Query("SELECT * FROM user_achievements WHERE user_id = :userId")
    suspend fun getUserAchievements(userId: Int): List<UserAchievementEntity>

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM user_achievements
            WHERE user_id = :userId AND achievement_id = :achievementId
        )
    """)
    suspend fun isAchievementUnlocked(
        userId: Int,
        achievementId: Int
    ): Boolean

    @Query("SELECT COUNT(*) FROM user_achievements WHERE user_id = :userId")
    suspend fun getUserAchievementCount(userId: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserAchievements(userAchievements: List<UserAchievementEntity>)

    @Query("""
    SELECT a.id, a.title, a.description, a.xp_bonus, a.condition_type
    FROM achievements a
    INNER JOIN user_achievements ua
        ON a.id = ua.achievement_id
    WHERE ua.user_id = :userId
""")
    suspend fun getUnlockedAchievementsForUser(userId: Int): List<AchievementEntity>
}