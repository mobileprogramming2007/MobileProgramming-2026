package com.example.mobileprogramminglabs.presentation.ui.screens.habit.util

import androidx.compose.ui.graphics.vector.ImageVector

data class HabitCardModel(
    val id: Int,
    val title: String,
    val subtitle: String,
    val icon: ImageVector? = null
)
