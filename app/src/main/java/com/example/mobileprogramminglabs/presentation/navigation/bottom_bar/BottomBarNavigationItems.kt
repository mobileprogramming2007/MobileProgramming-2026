package com.example.mobileprogramminglabs.presentation.navigation.bottom_bar

import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.navigation.Screen

object BottomBarNavigationItems {
    val items = listOf(
        BottomBarNavigationItem(
            titleId = R.string.home,
            iconRes = R.drawable.apple__streamline_unicons,
            route = Screen.HomeShortcut.route
        ),
        BottomBarNavigationItem(
            titleId = R.string.quest,
            iconRes = R.drawable.apple__streamline_unicons,
            route = Screen.Quest.route
        ),
        BottomBarNavigationItem(
            titleId = R.string.habit,
            iconRes = R.drawable.apple__streamline_unicons,
            route = Screen.Habit.route
        ),
        BottomBarNavigationItem(
            titleId = R.string.profile,
            iconRes = R.drawable.apple__streamline_unicons,
            route = Screen.Profile.route
        )
    )
}