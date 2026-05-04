package com.example.mobileprogramminglabs.presentation.ui.screens.achievement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.achievement.component.AchievementItem
import com.example.mobileprogramminglabs.presentation.ui.screens.achievement.util.AchievementData
import com.example.mobileprogramminglabs.presentation.ui.screens.error.ErrorScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.loading.LoadingScreen
import com.example.mobileprogramminglabs.presentation.view_model.achievement.AchievementNavigationEvent
import com.example.mobileprogramminglabs.presentation.view_model.achievement.AchievementUiState
import com.example.mobileprogramminglabs.presentation.view_model.achievement.AchievementViewModel

@Composable
fun AchievementScreen(
    viewModel: AchievementViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                AchievementNavigationEvent.Navigate -> Unit
                AchievementNavigationEvent.NavigateBack -> Unit
            }
        }
    }

    when (val state = uiState) {
        AchievementUiState.Init -> {
            LoadingScreen()
        }

        AchievementUiState.Loading -> {
            LoadingScreen()
        }

        is AchievementUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetryClick = { viewModel.resetUiState() }
            )
        }

        is AchievementUiState.Success -> {
            AchievementScreen(
                achievements = state.achievements
            )
        }
    }
}

@Composable
private fun AchievementScreen(
    achievements: List<AchievementData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.height_medium)
        )
    ) {
        item {
            Title(
                title = "Achievements",
                color = DeepTeal
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        }

        items(achievements, key = { it.id }) { achievement ->
            AchievementItem(achievement = achievement)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AchievementScreenPreview() {
    MaterialTheme {
        AchievementScreen(
            achievements = listOf(
                AchievementData(
                    id = 1,
                    title = "First Quest",
                    description = "something",
                    iconRes = R.drawable.achievement,
                    xpBonus = 20,
                    conditionType = "Complete 1 quest",
                    isUnlocked = true,
                    unlockedAt = "2026-04-30"
                ),
                AchievementData(
                    id = 2,
                    title = "3 Day Streak",
                    description = "something",
                    iconRes = R.drawable.achievement,
                    xpBonus = 30,
                    conditionType = "Maintain a 3 day streak",
                    isUnlocked = false
                )
            )
        )
    }
}