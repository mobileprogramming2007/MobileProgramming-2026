package com.example.mobileprogramminglabs.domain.data

import androidx.compose.ui.graphics.vector.ImageVector

data class HabitCardModel(
    val id: Int,
    val title: String,
    val subtitle: String,
    val icon: ImageVector? = null
)
