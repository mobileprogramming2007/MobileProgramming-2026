package com.example.mobileprogramminglabs.presentation.ui.screens.habit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.theme.Thistle
import com.example.mobileprogramminglabs.presentation.ui.components.RPGButton
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.error.ErrorScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.loading.LoadingScreen
import com.example.mobileprogramminglabs.presentation.view_model.habit.add_habit.AddHabitNavigationEvent
import com.example.mobileprogramminglabs.presentation.view_model.habit.add_habit.AddHabitUiState
import com.example.mobileprogramminglabs.presentation.view_model.habit.add_habit.AddHabitViewModel

@Composable
fun AddHabitScreen(
    viewModel: AddHabitViewModel,
    onHabitSaved: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var frequency by rememberSaveable { mutableStateOf("") }
    var completed by rememberSaveable { mutableStateOf(false) }

    val enabled = title.isNotBlank() &&
            description.isNotBlank() &&
            frequency.isNotBlank()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                AddHabitNavigationEvent.Navigate -> Unit
                AddHabitNavigationEvent.NavigateBack -> onHabitSaved()
            }
        }
    }

    when (val state = uiState) {
        is AddHabitUiState.Loading -> {
            LoadingScreen()
        }

        is AddHabitUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetryClick = { viewModel.resetUiState() }
            )
        }

        else -> {
            AddHabitScreenContent(
                title = title,
                description = description,
                frequency = frequency,
                completed = completed,
                enabled = enabled,
                onTitleChange = { title = it },
                onDescriptionChange = { description = it },
                onFrequencyChange = { frequency = it },
                onCompletedChange = { completed = it },
                onSaveClick = {
                    viewModel.addHabit(
                        title = title,
                        description = description,
                        frequency = frequency,
                        completed = completed
                    )
                }
            )
        }
    }
}

@Composable
private fun AddHabitScreenContent(
    title: String,
    description: String,
    frequency: String,
    completed: Boolean,
    enabled: Boolean,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onFrequencyChange: (String) -> Unit,
    onCompletedChange: (Boolean) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.padding_medium))
                .verticalScroll(rememberScrollState())
        ) {
            Title(
                title = "Add Habit",
                color = DeepTeal
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))

            OutlinedTextField(
                value = title,
                onValueChange = onTitleChange,
                label = { Text("Habit title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))

            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))

            OutlinedTextField(
                value = frequency,
                onValueChange = onFrequencyChange,
                label = { Text("Frequency") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = completed,
                    onCheckedChange = onCompletedChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Thistle,
                        uncheckedColor = RosyTaupe,
                        checkmarkColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.width_small)))
                Text(
                    text = "Mark as completed",
                    style = MaterialTheme.typography.bodyLarge,
                    color = DeepTeal
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))

            RPGButton(
                title = "Save Habit",
                enabled = enabled,
                onButtonClick = onSaveClick
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddHabitScreenPreview() {
    MaterialTheme {
        AddHabitScreenContent(
            title = "",
            description = "",
            frequency = "",
            completed = false,
            enabled = false,
            onTitleChange = {},
            onDescriptionChange = {},
            onFrequencyChange = {},
            onCompletedChange = {},
            onSaveClick = {}
        )
    }
}