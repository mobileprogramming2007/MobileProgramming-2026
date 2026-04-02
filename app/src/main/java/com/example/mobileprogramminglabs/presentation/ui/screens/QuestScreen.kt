package com.example.mobileprogramminglabs.presentation.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun QuestScreen() {
    var quests by remember {
        mutableStateOf(
            listOf(
                QuestData(1, "Study Kotlin", 20, false),
                QuestData(2, "Workout", 15, true),
                QuestData(3, "Drink Water", 10, false),
                QuestData(4, "Read 10 Pages", 25, false)
            )
        )
    }

    QuestScreen(
        quests = quests,
        onCheckedChange = { questId, isChecked ->
            quests.map { currentQuest ->
                if (currentQuest.id == questId) {
                    currentQuest.copy(isCompleted = isChecked)
                } else {
                    currentQuest
                }
            }
        },
        onDeleteClick = { questId ->
            quests.filter { it.id != questId }
        },
        onAddQuestClick = {}
    )
}
@Composable
private fun QuestScreen(
    quests: List<QuestData>,
    onCheckedChange: (Int, Boolean) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onAddQuestClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                QuestItem(
                    quest = quest,
                    onCheckedChange = { isChecked ->
                        onCheckedChange(quest.id, isChecked)
                    },
                    onDeleteClick = {
                        onDeleteClick(quest.id)
                    }
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xmedium)))
            }
        }
        FloatingActionButton(
            onClick = onAddQuestClick,
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
        var quests by remember {
            mutableStateOf(
                listOf(
                    QuestData(1, "Study Kotlin", 20, false),
                    QuestData(2, "Workout", 15, true),
                    QuestData(3, "Drink Water", 10, false),
                    QuestData(4, "Read 10 Pages", 25, false)
                )
            )
        }

        QuestScreen(
            quests = quests,
            onCheckedChange = { questId, isChecked ->
                quests.map { currentQuest ->
                    if (currentQuest.id == questId) {
                        currentQuest.copy(isCompleted = isChecked)
                    } else {
                        currentQuest
                    }
                }
            },
            onDeleteClick = { questId ->
                quests.filter { it.id != questId }
            },
            onAddQuestClick = {  },
        )
    }
}
