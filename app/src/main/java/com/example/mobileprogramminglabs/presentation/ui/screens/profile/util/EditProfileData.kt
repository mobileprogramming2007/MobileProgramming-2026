package com.example.mobileprogramminglabs.presentation.ui.screens.profile.util

data class EditProfileData(
    val id: Int = 0,
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val levelNo: Int = 1,
    val levelDescription: String = "",
    val xpTotal: Int = 0,
    val createdAt: Long = 0L
)