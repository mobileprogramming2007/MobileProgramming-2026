package com.example.mobileprogramminglabs.presentation.navigation

sealed class Screen(val route: String) {
    data object Register : Screen("register_screen")
    data object Login : Screen("login_screen")
    //data object Home : Screen("home_screen")
    data object Quest : Screen("quest_screen")
    //data object AddQuest : Screen("add_quest_screen")
    data object AddQuest : Screen("add_quest_screen/{source}") {
        fun createRoute(source: String): String {
            return "add_quest_screen/$source"
        }
    }
    data object Profile : Screen("profile_screen")
    data object Habit : Screen("habit_screen")
    data object HomeShortcut : Screen("home_shortcuts_screen")

    data object About : Screen("about_screen")
    data object Dashboard : Screen("dashboard_screen")

    data object Setting : Screen("setting_screen")

    /*data class Chat(val chatId: String) : Screen("chat_screen/{chatId}") {
        companion object {
            fun createRoute(chatId: String) = "chat_screen/$chatId"
        }
    }*/

    companion object {
        fun getBottomNavRoutes(): List<String> {
            return listOf(
                HomeShortcut.route,
                Quest.route,
                Habit.route,
                Profile.route
            )
        }
    }
}