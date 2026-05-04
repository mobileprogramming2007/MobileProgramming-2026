package com.example.mobileprogramminglabs.model.repository.user.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import com.example.mobileprogramminglabs.model.data.local.entity.UserEntity
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.util.RegisterUserData
import com.example.mobileprogramminglabs.presentation.ui.screens.profile.util.EditProfileData
import com.example.mobileprogramminglabs.presentation.ui.screens.profile.util.ProfileData
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData

fun UserEntity.toProfileData(): ProfileData {
    return ProfileData(
        name = fullName,
        levelNo = levelNo.toString(),
        levelDescription = levelDescription,
        profileStats = listOf(
            InfoRowData(
                title = "XP",
                additionalInfo = xpTotal.toString(),
                imageVector = Icons.Default.Star
            ),
            InfoRowData(
                title = "Level",
                additionalInfo = levelNo.toString(),
                imageVector = Icons.Default.Star
            )
        )
    )
}

fun EditProfileData.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        fullName = fullName,
        email = email,
        passwordHash = password,
        levelNo = levelNo,
        levelDescription = levelDescription,
        xpTotal = xpTotal,
        createdAt = createdAt
    )
}

fun UserEntity.toEditProfileData(): EditProfileData {
    return EditProfileData(
        id = id,
        fullName = fullName,
        email = email,
        password = passwordHash,
        confirmPassword = passwordHash,
        levelNo = levelNo,
        levelDescription = levelDescription,
        xpTotal = xpTotal,
        createdAt = createdAt
    )
}

fun RegisterUserData.toUserEntity(): UserEntity {
    return UserEntity(
        fullName = fullName,
        email = email,
        passwordHash = password
    )
}

