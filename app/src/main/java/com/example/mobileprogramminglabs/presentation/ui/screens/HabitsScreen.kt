package com.example.mobileprogramminglabs.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.components.HabitItem
import com.example.mobileprogramminglabs.presentation.ui.components.Title

@Composable
fun HabitsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Title(
            title = stringResource(R.string.habits),
            color = DeepTeal,
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_medium))
        )
        HabitItem(
            title = "Drink Water",
            streak = "5 days"
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_small)))
        HabitItem(
            title = "Read 10 Pages",
            streak = "3 days"
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_small)))
        HabitItem(
            title = "Sleep 8 Hours",
            streak = "7 days"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HabitsScreenPreview() {
    MaterialTheme {
        HabitsScreen()
    }
}
