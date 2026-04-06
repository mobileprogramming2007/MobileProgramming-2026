package com.example.mobileprogramminglabs.presentation.ui.screens.habit

import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTealDark
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.components.HabitCard
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.util.HabitCardModel

@Composable
fun FeaturedHabitsHorizontalGridScreen(
    habits: List<HabitCardModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AliceBlue)
            .padding(vertical = dimensionResource(R.dimen.padding_medium))
    ) {
        Title(
            title = "Habit Gallery",
            color = DeepTealDark,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .padding(top = dimensionResource(R.dimen.padding_medium)),
            contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            items(
                items = habits,
                key = { habit -> habit.id }
            ) { habit ->
                HabitCard(habit = habit)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FeaturedHabitsHorizontalGridScreenPreview() {
    FeaturedHabitsHorizontalGridScreen(
        habits = listOf(
            HabitCardModel(1, "Hydration", "Drink water regularly"),
            HabitCardModel(2, "Reading", "Read 10 pages daily", Icons.Default.CheckCircle),
            HabitCardModel(3, "Wellness", "Meditate for 10 minutes", Icons.Default.CheckCircle),
            HabitCardModel(4, "Energy", "Morning movement routine", Icons.Default.CheckCircle),
            HabitCardModel(5, "Focus", "Study without distractions", Icons.Default.CheckCircle),
            HabitCardModel(6, "Recovery", "Sleep 8 hours", Icons.Default.CheckCircle),
            HabitCardModel(7, "Energy", "Morning movement routine", Icons.Default.CheckCircle),
            HabitCardModel(8, "Focus", "Study without distractions", Icons.Default.CheckCircle),
            HabitCardModel(9, "Recovery", "Sleep 8 hours", Icons.Default.CheckCircle),
            HabitCardModel(10, "Hydration", "Drink water regularly"),
            HabitCardModel(11, "Reading", "Read 10 pages daily", Icons.Default.CheckCircle),
            HabitCardModel(12, "Wellness", "Meditate for 10 minutes", Icons.Default.CheckCircle),
            HabitCardModel(13, "Energy", "Morning movement routine", Icons.Default.CheckCircle),
            HabitCardModel(14, "Focus", "Study without distractions", Icons.Default.CheckCircle),
            HabitCardModel(15, "Recovery", "Sleep 8 hours", Icons.Default.CheckCircle),
            HabitCardModel(16, "Energy", "Morning movement routine", Icons.Default.CheckCircle),
            HabitCardModel(17, "Focus", "Study without distractions", Icons.Default.CheckCircle),
            HabitCardModel(18, "Recovery", "Sleep 8 hours", Icons.Default.CheckCircle)
        )
    )
}