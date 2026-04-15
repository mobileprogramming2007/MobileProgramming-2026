package com.example.mobileprogramminglabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobileprogramminglabs.presentation.navigation.NavGraph
import com.example.mobileprogramminglabs.presentation.navigation.Screen
import com.example.mobileprogramminglabs.presentation.navigation.Screen.Companion.getBottomNavRoutes
import com.example.mobileprogramminglabs.presentation.navigation.bottom_bar.BottomBarNavigationComponent
import com.example.mobileprogramminglabs.presentation.navigation.bottom_bar.BottomBarNavigationItems
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme

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
                val showBottomBar = currentRoute in getBottomNavRoutes()

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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                BottomBarNavigationComponent(
                    items = BottomBarNavigationItems.items,
                    selectedItemIndex = BottomBarNavigationItems.items.indexOfFirst {
                        it.route == navController.currentDestination?.route
                    }.coerceAtLeast(0),
                    onItemSelected = { index ->
                        val route = BottomBarNavigationItems.items[index].route
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
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
