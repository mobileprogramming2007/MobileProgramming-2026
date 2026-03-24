package com.example.mobileprogramminglabs.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.DustyOlive
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.theme.Thistle
import com.example.mobileprogramminglabs.presentation.ui.components.Title

@Composable
fun AddQuestScreen(modifier: Modifier = Modifier) {
    val categories = listOf("Study", "Health", "Fitness", "Reading")

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
                value = "",
                onValueChange = {},
                label = { Text(text = stringResource(R.string.quest_title)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
            OutlinedTextField(
                value = "",
                onValueChange = {},
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
                selectedItem = "Study",
                items = categories
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
                selected = true
            )
            DifficultyOption(
                label = "Medium",
                selected = false
            )
            DifficultyOption(
                label = "Hard",
                selected = false
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = false,
                    onCheckedChange = null,
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
            Button(
                onClick = {},
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DeepTeal,
                    disabledContainerColor = DustyOlive
                )
            ) {
                Text(
                    text = stringResource(R.string.save_quest),
                    color = AliceBlue
                )
            }
        }
    }
}

@Composable
fun CustomDropdownMenu(
    selectedItem: String,
    items: List<String>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensionResource(R.dimen.size_small)))
                .background(color = AliceBlue)
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = selectedItem,
                color = DeepTeal,
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = stringResource(R.string.category_dropdown),
                tint = DeepTeal
            )
        }
    }
}

@Composable
fun DifficultyOption(
    label: String,
    selected: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = RosyTaupe,
                unselectedColor = DustyOlive
            )
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.width_small)))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = DeepTeal
        )
    }
}

@Composable
fun QuestSnackbarHost(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier,
        snackbar = { snackbarData ->
            Snackbar(
                containerColor = DeepTeal,
                contentColor = AliceBlue,
                shape = RoundedCornerShape(dimensionResource(R.dimen.size_small))
            ) {
                Text(text = snackbarData.visuals.message)
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddQuestScreenPreview() {
    MaterialTheme {
        AddQuestScreen()
    }
}
