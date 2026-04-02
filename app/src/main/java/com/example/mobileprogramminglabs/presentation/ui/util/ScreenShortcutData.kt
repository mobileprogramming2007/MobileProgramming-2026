package com.example.mobileprogramminglabs.presentation.ui.util

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class ScreenShortcutData(
    val title: String,
    val icon: ImageVector,
    @DrawableRes val imageRes: Int
)