package com.example.mobileprogramminglabs.presentation.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.presentation.ui.components.InfoSection
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.dashboard.components.UserSectionCard
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.ui.screens.dashboard.components.HorizontalInfoSection
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData

@Composable
fun DashboardScreen() {
    DashboardScreen(
        levelNo = 3,
        achievementLevel = "Adventurer",
        currentXP = 120,
        maxXP = 200,
        todayQuests = listOf(
            InfoRowData("Study Kotlin"),
            InfoRowData("Workout"),
            InfoRowData("Drink Water"),
            InfoRowData("Workout"),
            InfoRowData("Drink Water"),
            InfoRowData("Workout"),
            InfoRowData("Drink Water")
        ),
        achievements = listOf(
            InfoRowData("First Quest", imageVector = Icons.Default.Face),
            InfoRowData("3 Day Streak", imageVector = Icons.Default.Face),
            InfoRowData("7 Day Streak", imageVector = Icons.Default.Face)
        ),
        quickStats = listOf(
            InfoRowData("Quests Completed", additionalInfo = "5"),
            InfoRowData("Total XP", additionalInfo = "120"),
            InfoRowData("Achievements", additionalInfo = "3"),
            InfoRowData("First Ques")
        )
    )
}

@Composable
private fun DashboardScreen(
    levelNo: Int,
    achievementLevel: String,
    currentXP: Int,
    maxXP: Int,
    todayQuests: List<InfoRowData>,
    achievements: List<InfoRowData>,
    quickStats: List<InfoRowData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AliceBlue)
            .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            color = RosyTaupe,
            title = stringResource(R.string.app_title)
        )
        UserSectionCard(
            levelNo = levelNo,
            achievementLevel = achievementLevel,
            currentXP = currentXP,
            maxXP = maxXP
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        InfoSection(
            title = stringResource(R.string.today_quests),
            rows = todayQuests
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        HorizontalInfoSection(
            title = stringResource(R.string.achievements),
            rows = achievements
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        InfoSection(
            title = stringResource(R.string.quick_stats),
            rows = quickStats
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    MaterialTheme {
        DashboardScreen(
            levelNo = 3,
            achievementLevel = "Adventurer",
            currentXP = 120,
            maxXP = 200,
            todayQuests = listOf(
                InfoRowData("Study Kotlin"),
                InfoRowData("Workout"),
                InfoRowData("Drink Water"),
                InfoRowData("Workout"),
                InfoRowData("Drink Water"),
                InfoRowData("Workout"),
                InfoRowData("Drink Water")
            ),
            achievements = listOf(
                InfoRowData("First Quest", imageVector = Icons.Default.Face),
                InfoRowData("3 Day Streak", imageVector = Icons.Default.Face),
                InfoRowData("7 Day Streak", imageVector = Icons.Default.Face)
            ),
            quickStats = listOf(
                InfoRowData("Quests Completed", additionalInfo = "5"),
                InfoRowData("Total XP", additionalInfo = "120"),
                InfoRowData("Achievements", additionalInfo = "3"),
                InfoRowData("First Ques")
            )
        )
    }
}
