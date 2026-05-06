package com.example.mobileprogramminglabs.presentation.navigation.bottom_bar

sealed interface BottomBarDestination {
    data object Home : BottomBarDestination
    data object Quest : BottomBarDestination
    data object Habit : BottomBarDestination
    data object Profile : BottomBarDestination
}