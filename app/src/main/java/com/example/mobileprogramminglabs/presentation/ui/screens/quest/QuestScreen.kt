package com.example.mobileprogramminglabs.presentation.ui.screens.quest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.components.QuestItem
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.util.QuestData

@Composable
fun QuestScreen(modifier: Modifier = Modifier) {
    val quests = listOf(
        QuestData(id = 1, title = "Study Kotlin", xp = 20, isCompleted = false),
        QuestData(id = 2, title = "Workout", xp = 15, isCompleted = true),
        QuestData(id = 3, title = "Drink Water", xp = 10, isCompleted = false),
        QuestData(id = 4, title = "Read 10 Pages", xp = 25, isCompleted = false)
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Title(
                title = stringResource(R.string.quests),
                color = DeepTeal
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            quests.forEach { quest ->
                QuestItem(quest = quest)
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xmedium)))
            }
        }
        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(dimensionResource(R.dimen.padding_medium)),
            containerColor = DeepTeal
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_quest),
                tint = AliceBlue
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuestScreenPreview() {
    MaterialTheme {
        QuestScreen()
    }
}
