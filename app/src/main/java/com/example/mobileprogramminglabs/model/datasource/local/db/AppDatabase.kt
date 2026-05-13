package com.example.mobileprogramminglabs.model.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobileprogramminglabs.model.datasource.local.dao.AchievementDao
import com.example.mobileprogramminglabs.model.datasource.local.dao.UserDao
import com.example.mobileprogramminglabs.model.datasource.local.entity.AchievementEntity
import com.example.mobileprogramminglabs.model.datasource.local.entity.UserAchievementEntity
import com.example.mobileprogramminglabs.model.datasource.local.entity.UserEntity

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
