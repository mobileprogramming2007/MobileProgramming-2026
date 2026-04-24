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
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.LoginScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.RegistrationScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.dashboard.DashboardScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.HabitsScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.home.HomeShortcutScreen
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
                onNavigate = {navController.navigate(Screen.HomeShortcut.route)},
                onLoginClick = {navController.navigate(Screen.Login.route)}
            )
        }

        composable(route = Screen.Login.route) {
            LoginScreen(
                viewModel = hiltViewModel(),
                onNavigate = { navController.navigate(Screen.HomeShortcut.route) },
                onRegisterClick = {navController.navigate(Screen.Register.route)}
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

        composable(route = Screen.HomeShortcut.route) {
            HomeShortcutScreen(
                viewModel = hiltViewModel(),
                onScreenClick = { route ->
                    navController.navigate(route)
                }//add logic
            )
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(viewModel = hiltViewModel())
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
    }
}
