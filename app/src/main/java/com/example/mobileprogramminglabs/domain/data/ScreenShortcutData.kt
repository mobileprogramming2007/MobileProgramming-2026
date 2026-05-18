package com.example.mobileprogramminglabs.domain.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class ScreenShortcutData(
    val title: String,
    val icon: ImageVector,
    @DrawableRes val imageRes: Int
)