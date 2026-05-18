package com.example.mobileprogramminglabs.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun register(email: String, password: String)
    suspend fun login(email: String, password: String)
    fun logout()
    fun getCurrentUserId(): String?
    suspend fun signInWithGoogle(idToken: String): FirebaseUser?

}