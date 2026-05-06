package com.example.mobileprogramminglabs.presentation.navigation

sealed class Screen(val route: String) {
    data object Register : Screen("register_screen")
    data object Login : Screen("login_screen")
    data object Quest : Screen("quest_screen")
    data object AddQuest : Screen("add_quest_screen/{source}") {
        fun createRoute(source: String): String {
            return "add_quest_screen/$source"
        }
    }
    data object Profile : Screen("profile_screen/{userId}") {
        fun createRoute(userId: Int): String {
            return "profile_screen/$userId"
        }
    }
    data object EditProfile : Screen("edit_profile_screen")
    data object Habit : Screen("habit_screen")
    data object HomeShortcut : Screen("home_shortcuts_screen/{userId}") {
        fun createRoute(userId: Int): String = "home_shortcuts_screen/$userId"
    }
    data object Achievement : Screen("achievement_screen")

    data object About : Screen("about_screen")
    data object Dashboard : Screen("dashboard_screen")

    data object Setting : Screen("setting_screen")

    companion object {
        fun isBottomBarRoute(route: String?): Boolean {
            return route == Quest.route ||
                route == Habit.route ||
                route?.startsWith("home_shortcuts_screen/") == true ||
                route?.startsWith("profile_screen/") == true
        }
    }
}