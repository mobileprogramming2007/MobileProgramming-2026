package com.example.mobileprogramminglabs.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.presentation.ui.components.InfoSection
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.components.UserSectionCard
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
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
            levelNo = 3,
            achievementLevel = "Adventurer",
            currentXP = 120,
            maxXP = 200
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        InfoSection(
            title = stringResource(R.string.today_quests),
            rows = listOf(
                InfoRowData("Study Kotlin", imageVector = Icons.Default.Star),
                InfoRowData("Workout", imageVector = Icons.Default.Star),
                InfoRowData("Drink Water", imageVector = Icons.Default.Star)
            )
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        InfoSection(
            title = stringResource(R.string.achievements),
            rows = listOf(
                InfoRowData("First Quest", imageVector = Icons.Default.Face),
                InfoRowData("3 Day Streak", imageVector = Icons.Default.Face)
            )
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        InfoSection(
            title = stringResource(R.string.quick_stats),
            rows = listOf(
                InfoRowData("Quests Completed", additionalInfo = "5"),
                InfoRowData("Total XP", additionalInfo = "120"),
                InfoRowData("Achievements", additionalInfo = "3"),
                InfoRowData("First Ques")
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    MaterialTheme {
        DashboardScreen()
    }
}
