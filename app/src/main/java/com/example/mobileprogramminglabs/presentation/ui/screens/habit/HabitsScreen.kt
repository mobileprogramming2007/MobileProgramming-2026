package com.example.mobileprogramminglabs.presentation.ui.screens.habit

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.components.HabitItem
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.error.ErrorScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.util.HabitModel
import com.example.mobileprogramminglabs.presentation.ui.screens.loading.LoadingScreen
import com.example.mobileprogramminglabs.presentation.view_model.habit.HabitNavigationEvent
import com.example.mobileprogramminglabs.presentation.view_model.habit.HabitUiState
import com.example.mobileprogramminglabs.presentation.view_model.habit.HabitViewModel

@Composable
fun HabitsScreen(
    viewModel: HabitViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                habits = state.habits
            )
        }

        else -> {
            //no-op
        }
    }
}

@Composable
private fun HabitsScreen(
    habits: List<HabitModel>,
    modifier: Modifier = Modifier
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
        items(habits){ habit ->
            HabitItem(
                title = habit.title,
                streak = habit.streak
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_small)))

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HabitsScreenPreview() {
    MaterialTheme {
        HabitsScreen(
            habits = listOf(
                HabitModel(title = "Drink Water", streak = "5 days"),
                HabitModel(title = "Read 10 Pages", streak = "3 days"),
                HabitModel(title = "Sleep 8 Hours", streak = "7 days"),
                HabitModel(title = "Morning Walk", streak = "4 days"),
                HabitModel(title = "Meditate", streak = "6 days"),
                HabitModel(title = "Write Journal", streak = "2 days"),
                HabitModel(title = "No Sugar", streak = "8 days"),
                HabitModel(title = "Study Kotlin", streak = "10 days"),
                HabitModel(title = "Exercise", streak = "9 days"),
                HabitModel(title = "Stretching", streak = "4 days"),
                HabitModel(title = "Practice English", streak = "12 days"),
                HabitModel(title = "Eat Breakfast", streak = "11 days"),
                HabitModel(title = "Limit Screen Time", streak = "3 days"),
                HabitModel(title = "Go to Bed Early", streak = "5 days"),
                HabitModel(title = "Review Notes", streak = "7 days"),
                HabitModel(title = "Clean Room", streak = "2 days"),
                HabitModel(title = "Take Vitamins", streak = "14 days"),
                HabitModel(title = "Daily Planning", streak = "6 days"),
                HabitModel(title = "Learn Compose", streak = "8 days"),
                HabitModel(title = "Evening Walk", streak = "4 days")
            )
        )
    }
}
