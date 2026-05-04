package com.example.mobileprogramminglabs.presentation.navigation.bottom_bar

import com.example.mobileprogramminglabs.R


object BottomBarNavigationItems {
    val items = listOf(
        BottomBarNavigationItem(
            titleId = R.string.home,
            iconRes = R.drawable.apple__streamline_unicons,
            destination = BottomBarDestination.Home
        ),
        BottomBarNavigationItem(
            titleId = R.string.quest,
            iconRes = R.drawable.apple__streamline_unicons,
            destination = BottomBarDestination.Quest
        ),
        BottomBarNavigationItem(
            titleId = R.string.habit,
            iconRes = R.drawable.apple__streamline_unicons,
            destination = BottomBarDestination.Habit
        ),
        BottomBarNavigationItem(
            titleId = R.string.profile,
            iconRes = R.drawable.apple__streamline_unicons,
            destination = BottomBarDestination.Profile
        )
    )
}
