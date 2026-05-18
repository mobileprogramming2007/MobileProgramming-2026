package com.example.mobileprogramminglabs.presentation.ui.screens.habit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.components.HabitItem
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.error.ErrorScreen
import com.example.mobileprogramminglabs.domain.data.HabitModel
import com.example.mobileprogramminglabs.presentation.ui.screens.loading.LoadingScreen
import com.example.mobileprogramminglabs.presentation.view_model.habit.HabitNavigationEvent
import com.example.mobileprogramminglabs.presentation.view_model.habit.HabitUiState
import com.example.mobileprogramminglabs.presentation.view_model.habit.HabitViewModel

@Composable
fun HabitsScreen(
    viewModel: HabitViewModel,
    onNavigateToAddHabit: () -> Unit,
    onNavigateToEditHabit: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    var selectedHabit by remember { mutableStateOf<HabitModel?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.loadHabits()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                HabitNavigationEvent.Navigate -> Unit
                HabitNavigationEvent.NavigateBack -> Unit
            }
        }
    }

    when (val state = uiState) {
        is HabitUiState.Loading -> {
            LoadingScreen()
        }

        is HabitUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetryClick = { viewModel.resetUiState() }
            )
        }

        is HabitUiState.Success -> {
            HabitsScreen(
                habits = state.habits,
                onAddHabitClick = onNavigateToAddHabit,
                onDeleteHabitClick = { habit ->
                    selectedHabit = habit
                    showDeleteDialog = true
                },
                onEditHabitClick = { habit ->
                    onNavigateToEditHabit(habit.id)
                }
            )
        }

        else -> Unit
    }

    if (showDeleteDialog && selectedHabit != null) {
        DeleteHabitDialog(
            habitTitle = selectedHabit!!.title,
            onConfirm = {
                viewModel.deleteHabit(selectedHabit!!.id)
                showDeleteDialog = false
                selectedHabit = null
            },
            onDismiss = {
                showDeleteDialog = false
                selectedHabit = null
            }
        )
    }
}

/*
@Composable
fun HabitsScreen(
    viewModel: HabitViewModel,
    onNavigateToAddHabit: () -> Unit,
    onNavigateToEditHabit: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    var habitToDelete by remember { mutableStateOf<HabitModel?>(null) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.loadHabits()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                HabitNavigationEvent.Navigate -> Unit
                HabitNavigationEvent.NavigateBack -> Unit
            }
        }
    }

    when (val state = uiState) {
        is HabitUiState.Loading -> {
            LoadingScreen()
        }

        is HabitUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetryClick = { viewModel.resetUiState() }
            )
        }

        is HabitUiState.Success -> {
            HabitsScreen(
                habits = state.habits,
                onAddHabitClick = onNavigateToAddHabit,
                onDeleteHabitClick = { habit ->
                    habitToDelete = habit
                },
                onEditHabitClick = { habit ->
                    onNavigateToEditHabit(habit.id)
                }
            )

            habitToDelete?.let { selectedHabit ->
                DeleteHabitDialog(
                    habitTitle = selectedHabit.title,
                    onConfirm = {
                        viewModel.deleteHabit(selectedHabit.id)
                        habitToDelete = null
                    },
                    onDismiss = {
                        habitToDelete = null
                    }
                )
            }
        }

        else -> {
            //no-op
        }
    }
}
*/
@Composable
private fun HabitsScreen(
    habits: List<HabitModel>,
    onDeleteHabitClick: (HabitModel) -> Unit,
    onEditHabitClick: (HabitModel) -> Unit,
    onAddHabitClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            item {
                Title(
                    title = stringResource(R.string.habits),
                    color = DeepTeal,
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_medium))
                )
            }
            items(habits) { habit ->
                HabitItem(
                    title = habit.title,
                    streak = habit.streak,
                    onDeleteClick = { onDeleteHabitClick(habit) },
                    onEditClick = { onEditHabitClick(habit) }
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_small)))
            }
        }
        FloatingActionButton(
            onClick = onAddHabitClick,
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

@Composable
private fun DeleteHabitDialog(
    habitTitle: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Delete Habit")
        },
        text = {
            Text(text = "Are you sure you want to delete \"$habitTitle\"?")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HabitsScreenPreview() {
    MaterialTheme {
        HabitsScreen(
            onAddHabitClick = {},
            onDeleteHabitClick = {},
            onEditHabitClick = {},
            habits = listOf(
                HabitModel(id = 1, title = "Drink Water", streak = "5 days"),
                HabitModel(id = 2, title = "Read 10 Pages", streak = "3 days"),
            )
        )
    }
}
