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
                onLoginClick = {navController.navigate(Screen.Login.route)},
                onRegisterClick = {navController.navigate(Screen.HomeShortcut.route)}
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
            HabitsScreen()
        }

        composable(route = Screen.HomeShortcut.route) {
            HomeShortcutScreen(
                onScreenClick = { route ->
                    navController.navigate(route)
                }//add logic
            )
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen()
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
                onSaveButtonClick = {
                    navController.navigate(Screen.Quest.route)
                }
            )
        }

        composable(route = Screen.Quest.route) {
            QuestScreen(
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

/*
        composable(route = Screen.SignUp.route) {
            SignUpScreen(
                viewModel = hiltViewModel(),
                onSignInClick = { navController.navigate(Screen.SignIn.route) },
                navigate = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }*/
