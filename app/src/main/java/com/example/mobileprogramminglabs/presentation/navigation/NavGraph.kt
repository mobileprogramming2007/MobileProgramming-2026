package com.example.mobileprogramminglabs.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mobileprogramminglabs.presentation.ui.screens.about.AboutScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.achievement.AchievementScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.LoginScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.RegistrationScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.dashboard.DashboardScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.HabitsScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.home.HomeShortcutScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.profile.EditProfileScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.profile.ProfileScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.quest.AddQuestScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.quest.QuestScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.settings.SettingsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Register.route) {
            RegistrationScreen(
                viewModel = hiltViewModel(),
                onNavigate = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(route = Screen.Login.route) {
            LoginScreen(
                viewModel = hiltViewModel(),
                onNavigate = { userId ->
                    navController.navigate(Screen.HomeShortcut.createRoute(userId)) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate(Screen.Register.route) }
            )
        }

        composable(route = Screen.About.route) {
            AboutScreen()
        }

        composable(route = Screen.Dashboard.route) {
            DashboardScreen()
        }

        composable(route = Screen.Habit.route) {
            HabitsScreen(viewModel = hiltViewModel())
        }

        composable(
            route = Screen.HomeShortcut.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: return@composable

            HomeShortcutScreen(
                viewModel = hiltViewModel(),
                onScreenClick = { destination ->
                    when (destination) {
                        "profile" -> navController.navigate(Screen.Profile.createRoute(userId))
                        Screen.Quest.route -> navController.navigate(Screen.Quest.route)
                        Screen.Habit.route -> navController.navigate(Screen.Habit.route)
                        Screen.Achievement.route -> navController.navigate(Screen.Achievement.route)
                        Screen.Dashboard.route -> navController.navigate(Screen.Dashboard.route)
                        Screen.About.route -> navController.navigate(Screen.About.route)
                        Screen.Setting.route -> navController.navigate(Screen.Setting.route)
                    }
                }
            )
        }

        composable(
            route = Screen.Profile.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                }
            )
        ) {
            ProfileScreen(
                viewModel = hiltViewModel(),
                onEditNavigate = { navController.navigate(Screen.EditProfile.route) },
                onLogoutNavigate = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onAchievementsClick = { navController.navigate(Screen.Achievement.route) }
            )
        }

        composable(route = Screen.EditProfile.route) {
            EditProfileScreen(
                viewModel = hiltViewModel(),
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.AddQuest.route,
            arguments = listOf(
                navArgument("source") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val source = backStackEntry.arguments?.getString("source").orEmpty()

            AddQuestScreen(
                source = source,
                viewModel = hiltViewModel(),
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.Quest.route) {
            QuestScreen(
                viewModel = hiltViewModel(),
                onAddQuestClick = {
                    navController.navigate(Screen.AddQuest.createRoute("quest_screen"))
                }
            )
        }

        composable(route = Screen.Setting.route) {
            SettingsScreen()
        }

        composable(route = Screen.Achievement.route) {
            AchievementScreen(
                viewModel = hiltViewModel()
            )
        }
    }
}
