package com.example.mobileprogramminglabs.presentation.ui.screens.quest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.screens.quest.components.QuestItem
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.error.ErrorScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.loading.LoadingScreen
import com.example.mobileprogramminglabs.domain.data.QuestData
import com.example.mobileprogramminglabs.presentation.view_model.quest.quest.QuestNavigationEvent
import com.example.mobileprogramminglabs.presentation.view_model.quest.quest.QuestUiState
import com.example.mobileprogramminglabs.presentation.view_model.quest.quest.QuestViewModel

@Composable
fun QuestScreen(
    viewModel: QuestViewModel,
    onAddQuestClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val quests by viewModel.quests.collectAsStateWithLifecycle()

    val exportMessage by viewModel.exportMessage.collectAsStateWithLifecycle()
    val exportError by viewModel.exportError.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }


    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.loadQuests()
    }

    LaunchedEffect(exportMessage, exportError) {
        when {
            exportMessage != null -> {
                snackbarHostState.showSnackbar(exportMessage!!)
                viewModel.clearExportState()
            }

            exportError != null -> {
                snackbarHostState.showSnackbar("Export failed: $exportError")
                viewModel.clearExportState()
            }
        }
    }


    when (val state = uiState) {
        QuestUiState.Loading -> {
            LoadingScreen()
        }

        is QuestUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetryClick = { viewModel.loadQuests() }
            )
        }

        else -> {
            QuestScreen(
                quests = quests,
                onCheckedChange = { questId, isChecked ->
                    viewModel.toggleQuest(questId, isChecked)
                },
                onDeleteClick = { questId ->
                    viewModel.deleteQuest(questId)
                },
                onAddQuestClick = onAddQuestClick,
                onExportClick = {
                    viewModel.exportQuests()
                },
                snackbarHostState = snackbarHostState

            )
        }
    }
}

@Composable
private fun QuestScreen(
    quests: List<QuestData>,
    onCheckedChange: (String, Boolean) -> Unit,
    onDeleteClick: (String) -> Unit,
    onAddQuestClick: () -> Unit,
    onExportClick: () -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(dimensionResource(R.dimen.padding_medium))
        )
        {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.height_xmedium))
            ) {
                item {
                    Title(
                        title = stringResource(R.string.quests),
                        color = DeepTeal
                    )
                }
                item {
                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(R.dimen.padding_medium)
                        )
                    )
                }
                items(
                    items = quests,
                    key = { quest -> quest.id }
                ) { quest ->
                    QuestItem(
                        quest = quest,
                        modifier = Modifier.animateItem(),
                        onCheckedChange = { isChecked ->
                            onCheckedChange(quest.id, isChecked)
                        },
                        onDeleteClick = {
                            onDeleteClick(quest.id)
                        }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(dimensionResource(R.dimen.padding_medium)),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.padding_small)
                )
            ) {
                FloatingActionButton(
                    onClick = onExportClick,
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Export quests",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }

                FloatingActionButton(
                    onClick = onAddQuestClick,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_quest),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

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
                    QuestData("1", "Study Kotlin", "20", 12,false),
                    QuestData("2", "Workout", "15", 10,true),
                    QuestData("3", "Drink Water", "10", 11,false),
                    QuestData("4", "Read 10 Pages", "25", 12,false),
                    QuestData("5", "Study Kotlin", "20",213, false),
                )
            )
        }

        val snackbarHostState = remember { SnackbarHostState() }

        QuestScreen(
            quests = quests,
            onCheckedChange = { questId, isChecked ->
                quests = quests.map { currentQuest ->
                    if (currentQuest.id == questId) {
                        currentQuest.copy(isCompleted = isChecked)
                    } else {
                        currentQuest
                    }
                }
            },
            onDeleteClick = { questId ->
                quests = quests.filter { it.id != questId }
            },
            onAddQuestClick = {  },
            onExportClick = {},
            snackbarHostState = snackbarHostState
        )
    }
}
