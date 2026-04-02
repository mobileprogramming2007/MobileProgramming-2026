package com.example.mobileprogramminglabs.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.theme.Thistle
import com.example.mobileprogramminglabs.presentation.ui.components.CustomDropdownMenu
import com.example.mobileprogramminglabs.presentation.ui.components.DifficultyOption
import com.example.mobileprogramminglabs.presentation.ui.components.QuestSnackbarHost
import com.example.mobileprogramminglabs.presentation.ui.components.RPGButton
import com.example.mobileprogramminglabs.presentation.ui.components.Title

@Composable
fun AddQuestScreen() {
    var questTitle by rememberSaveable { mutableStateOf("") }
    var xpReward by rememberSaveable { mutableStateOf("") }
    var checkedDailyQuest by rememberSaveable { mutableStateOf(false) }
    var selectedItem by rememberSaveable { mutableStateOf("Health") }
    var selectedDifficulty by rememberSaveable { mutableStateOf("Easy") }
    var expanded by remember { mutableStateOf(false) }

    val enabled = questTitle.isNotBlank() &&
            xpReward.isNotBlank() &&
            selectedItem.isNotBlank() &&
            selectedDifficulty.isNotBlank()

    AddQuestScreen(
        questTitle = questTitle,
        xpReward = xpReward,
        checkedDailyQuest = checkedDailyQuest,
        enabled = enabled,
        selectedItem = selectedItem,
        selectedDifficulty = selectedDifficulty,
        expanded = expanded,
        onSaveButtonClick = { },
        onDifficultyOptionClick = { selectedDifficulty = it },
        onQuestTitleChange = { questTitle = it },
        onXPRewardChange = { xpReward = it },
        onCheckedDailyQuestChange = { checkedDailyQuest = it },
        onExpandedChange = { expanded = it },
        onSelectedItemChange = { selectedItem = it }
    )
}

@Composable
private fun AddQuestScreen(
    questTitle: String,
    xpReward: String,
    checkedDailyQuest: Boolean,
    enabled: Boolean,
    selectedItem: String,
    selectedDifficulty: String,
    expanded: Boolean,
    onSaveButtonClick: () -> Unit,
    onDifficultyOptionClick: (String) -> Unit,
    onQuestTitleChange: (String) -> Unit,
    onXPRewardChange: (String) -> Unit,
    onCheckedDailyQuestChange: (Boolean) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onSelectedItemChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        snackbarHost = {
            QuestSnackbarHost(snackbarHostState = SnackbarHostState())
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Title(
                title = stringResource(R.string.add_quest),
                color = DeepTeal
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
            OutlinedTextField(
                value = questTitle,
                onValueChange = onQuestTitleChange,
                label = { Text(text = stringResource(R.string.quest_title)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
            OutlinedTextField(
                value = xpReward,
                onValueChange = onXPRewardChange,
                label = { Text(text = stringResource(R.string.xp_reward)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
            Text(
                text = stringResource(R.string.category),
                style = MaterialTheme.typography.titleMedium,
                color = DeepTeal,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_small)))
            CustomDropdownMenu(
                selectedItem = selectedItem,
                items = listOf("Health", "Study", "Fitness", "Productivity"),
                expanded = expanded,
                onExpandedChange = onExpandedChange,
                onItemSelected = onSelectedItemChange
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
            Text(
                text = stringResource(R.string.difficulty),
                style = MaterialTheme.typography.titleMedium,
                color = DeepTeal,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_small)))
            DifficultyOption(
                label = "Easy",
                onDifficultyOptionClick = { onDifficultyOptionClick("Easy") },
                selected = selectedDifficulty == "Easy"
            )
            DifficultyOption(
                label = "Medium",
                onDifficultyOptionClick = { onDifficultyOptionClick("Medium") },
                selected = selectedDifficulty == "Medium"
            )
            DifficultyOption(
                label = "Hard",
                onDifficultyOptionClick = { onDifficultyOptionClick("Hard") },
                selected = selectedDifficulty == "Hard"
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedDailyQuest,
                    onCheckedChange = onCheckedDailyQuestChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Thistle,
                        uncheckedColor = RosyTaupe,
                        checkmarkColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.width_small)))
                Text(
                    text = stringResource(R.string.mark_as_daily_quest),
                    style = MaterialTheme.typography.bodyLarge,
                    color = DeepTeal
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
            RPGButton(
                enabled = enabled,
                onButtonClick = onSaveButtonClick
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddQuestScreenPreview() {
    MaterialTheme {

        var questTitle by rememberSaveable { mutableStateOf("") }
        var xpReward by rememberSaveable { mutableStateOf("") }
        var checkedDailyQuest by rememberSaveable { mutableStateOf(false) }
        var selectedItem by rememberSaveable { mutableStateOf("Health") }
        var selectedDifficulty by rememberSaveable { mutableStateOf("Easy") }
        var expanded by remember { mutableStateOf(false) }

        val enabled = questTitle.isNotBlank() &&
                xpReward.isNotBlank() &&
                selectedItem.isNotBlank() &&
                selectedDifficulty.isNotBlank()

        AddQuestScreen(
            questTitle = questTitle,
            xpReward = xpReward,
            checkedDailyQuest = checkedDailyQuest,
            enabled = enabled,
            selectedItem = selectedItem,
            selectedDifficulty = selectedDifficulty,
            expanded = expanded,
            onSaveButtonClick = {},
            onDifficultyOptionClick = { selectedDifficulty = it },
            onQuestTitleChange = { questTitle = it },
            onXPRewardChange = { xpReward = it },
            onCheckedDailyQuestChange = { checkedDailyQuest = it },
            onExpandedChange = { expanded = it },
            onSelectedItemChange = { selectedItem = it },
        )
    }
}
