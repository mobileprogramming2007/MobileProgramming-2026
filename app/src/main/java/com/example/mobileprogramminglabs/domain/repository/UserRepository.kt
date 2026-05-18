package com.example.mobileprogramminglabs.domain.repository

import com.example.mobileprogramminglabs.model.datasource.local.entity.UserEntity
import com.example.mobileprogramminglabs.domain.data.RegisterUserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(userData: RegisterUserData)
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity?
    suspend fun getUserByEmail(email: String): UserEntity?
    suspend fun getUserById(id: Int): UserEntity?
    fun observeUserById(id: Int): Flow<UserEntity?>
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
}
