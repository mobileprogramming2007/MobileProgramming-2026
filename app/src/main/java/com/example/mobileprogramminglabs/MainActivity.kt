package com.example.mobileprogramminglabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobileprogramminglabs.presentation.navigation.NavGraph
import com.example.mobileprogramminglabs.presentation.navigation.Screen
import com.example.mobileprogramminglabs.presentation.navigation.bottom_bar.BottomBarDestination
import com.example.mobileprogramminglabs.presentation.navigation.bottom_bar.BottomBarNavigationComponent
import com.example.mobileprogramminglabs.presentation.navigation.bottom_bar.BottomBarNavigationItems
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileProgrammingLabsTheme {
                val navController = rememberNavController()

                val currentBackStackEntry by navController
                    .currentBackStackEntryFlow
                    .collectAsStateWithLifecycle(initialValue = null)

                val currentRoute = currentBackStackEntry?.destination?.route
                val showBottomBar = Screen.isBottomBarRoute(currentRoute)

                BottomNavScaffold(
                    navController = navController,
                    startDestination = Screen.Login.route,
                    showBottomBar = showBottomBar
                )
            }
        }
    }
}

@Composable
fun BottomNavScaffold(
    navController: NavHostController,
    startDestination: String,
    showBottomBar: Boolean
) {
    val currentBackStackEntry by navController
        .currentBackStackEntryFlow
        .collectAsStateWithLifecycle(initialValue = null)

    val currentRoute = currentBackStackEntry?.destination?.route

    val selectedItemIndex = BottomBarNavigationItems.items.indexOfFirst { item ->
        when (item.destination) {
            BottomBarDestination.Home ->
                currentRoute?.startsWith("home_shortcuts_screen/") == true

            BottomBarDestination.Quest ->
                currentRoute == Screen.Quest.route

            BottomBarDestination.Habit ->
                currentRoute == Screen.Habit.route

            BottomBarDestination.Profile ->
                currentRoute?.startsWith("profile_screen/") == true
        }
    }.coerceAtLeast(0)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                BottomBarNavigationComponent(
                    items = BottomBarNavigationItems.items,
                    selectedItemIndex = selectedItemIndex,
                    onItemSelected = { index ->
                        val selectedDestination = BottomBarNavigationItems.items[index].destination

                        val currentUserId =
                            currentBackStackEntry?.arguments?.getInt("userId")

                        when (selectedDestination) {
                            BottomBarDestination.Home -> {
                                val userId = currentUserId ?: return@BottomBarNavigationComponent
                                navController.navigate(Screen.HomeShortcut.createRoute(userId)) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }

                            BottomBarDestination.Quest -> {
                                navController.navigate(Screen.Quest.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }

                            BottomBarDestination.Habit -> {
                                navController.navigate(Screen.Habit.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }

                            BottomBarDestination.Profile -> {
                                val userId = currentUserId ?: return@BottomBarNavigationComponent
                                navController.navigate(Screen.Profile.createRoute(userId)) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding: PaddingValues ->
        NavGraph(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
