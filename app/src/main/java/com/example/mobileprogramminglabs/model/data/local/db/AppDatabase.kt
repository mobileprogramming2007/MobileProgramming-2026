package com.example.mobileprogramminglabs.model.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobileprogramminglabs.model.data.local.dao.AchievementDao
import com.example.mobileprogramminglabs.model.data.local.dao.UserDao
import com.example.mobileprogramminglabs.model.data.local.entity.AchievementEntity
import com.example.mobileprogramminglabs.model.data.local.entity.UserAchievementEntity
import com.example.mobileprogramminglabs.model.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        AchievementEntity::class,
        UserAchievementEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun achievementDao(): AchievementDao

}
