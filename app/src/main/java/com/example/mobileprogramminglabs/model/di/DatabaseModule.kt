package com.example.mobileprogramminglabs.model.di

import android.content.Context
import androidx.room.Room
import com.example.mobileprogramminglabs.model.data.local.dao.AchievementDao
import com.example.mobileprogramminglabs.model.data.local.dao.UserDao
import com.example.mobileprogramminglabs.model.data.local.db.AppDatabase
import com.example.mobileprogramminglabs.model.repository.achievement.AchievementRepository
import com.example.mobileprogramminglabs.model.repository.achievement.AchievementRepositoryImpl
import com.example.mobileprogramminglabs.model.repository.user.SessionRepository
import com.example.mobileprogramminglabs.model.repository.user.SessionRepositoryImpl
import com.example.mobileprogramminglabs.model.repository.user.UserRepository
import com.example.mobileprogramminglabs.model.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "liferPg_database"
            )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideAchievementDao(database: AppDatabase): AchievementDao {
        return database.achievementDao()
    }

    @Provides
    @Singleton
    fun provideAchievementRepository(
        achievementDao: AchievementDao
    ): AchievementRepository {
        return AchievementRepositoryImpl(achievementDao)
    }

    @Provides
    @Singleton
    fun provideSessionRepository(
        @ApplicationContext context: Context
    ): SessionRepository {
        return SessionRepositoryImpl(context)
    }
}
