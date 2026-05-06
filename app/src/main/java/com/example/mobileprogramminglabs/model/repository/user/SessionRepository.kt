package com.example.mobileprogramminglabs.model.repository.user

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun saveLoggedInUserId(userId: Int)
    fun observeLoggedInUserId(): Flow<Int?>
    suspend fun clearSession()
}
