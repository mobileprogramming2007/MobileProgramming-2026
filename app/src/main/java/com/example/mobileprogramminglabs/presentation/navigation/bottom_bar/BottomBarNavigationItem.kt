package com.example.mobileprogramminglabs.presentation.navigation.bottom_bar

import androidx.annotation.StringRes

data class BottomBarNavigationItem(
    @StringRes val titleId: Int,
    val iconRes: Int,
    val destination: BottomBarDestination,
    val unreadCount: Int? = 0
)