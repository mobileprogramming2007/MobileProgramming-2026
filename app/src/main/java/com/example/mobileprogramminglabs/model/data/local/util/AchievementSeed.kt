package com.example.mobileprogramminglabs.model.data.local.util

import com.example.mobileprogramminglabs.model.data.local.entity.AchievementEntity

object AchievementSeed {

    val defaultAchievements = listOf(
        AchievementEntity(
            title = "First Quest",
            description = "Complete your first quest",
            xpBonus = 20,
            conditionType = "FIRST_QUEST"
        ),
        AchievementEntity(
            title = "XP Beginner",
            description = "Reach 100 XP",
            xpBonus = 30,
            conditionType = "XP_100"
        ),
        AchievementEntity(
            title = "Quest Explorer",
            description = "Complete 5 quests",
            xpBonus = 40,
            conditionType = "QUESTS_5"
        ),
        AchievementEntity(
            title = "Level Up",
            description = "Reach level 3",
            xpBonus = 50,
            conditionType = "LEVEL_3"
        )
    )
}