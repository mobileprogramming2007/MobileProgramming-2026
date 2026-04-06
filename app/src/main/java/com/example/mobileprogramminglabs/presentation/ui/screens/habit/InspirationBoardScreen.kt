package com.example.mobileprogramminglabs.presentation.ui.screens.habit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlueLight
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.components.HabitCard
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.util.HabitCardModel

@Composable
fun InspirationBoardScreen(
    cards: List<HabitCardModel>,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .background(AliceBlueLight)
            .padding(vertical = 24.dp),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium)),
        verticalItemSpacing = dimensionResource(R.dimen.padding_medium),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        item {
            Title(
                title = "Inspiration Board",
                color = DeepTeal,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
            )
        }

        items(
            items = cards,
            key = { card -> card.id }
        ) { card ->
            HabitCard(habit = card)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InspirationBoardScreenPreview() {
    InspirationBoardScreen(
        cards = listOf(
            HabitCardModel(1, "Start Small", "Progress does not need to be dramatic. Small steps repeated daily create strong long-term results."),
            HabitCardModel(2, "Keep Going", "A missed day does not erase your effort."),
            HabitCardModel(3, "Be Consistent", "Consistency matters more than intensity when building habits that last."),
            HabitCardModel(4, "Rest Well", "Recovery is part of progress, not the opposite of it."),
            HabitCardModel(5, "Stay Focused", "Remove one distraction today and your work becomes lighter."),
            HabitCardModel(6, "Celebrate Wins", "Even a small completed task deserves recognition."),
            HabitCardModel(7, "Rest Well", "Recovery is part of progress, not the opposite of it."),
            HabitCardModel(8, "Stay Focused", "Remove one distraction today and your work becomes lighter."),
            HabitCardModel(9, "Celebrate Wins", "Even a small completed task deserves recognition."),
            HabitCardModel(10, "Celebrate Wins", "Even a small completed task deserves recognition."),
            HabitCardModel(11, "Rest Well", "Recovery is part of progress, not the opposite of it."),
            HabitCardModel(12, "Stay Focused", "Remove one distraction today and your work becomes lighter."),
            HabitCardModel(13, "Celebrate Wins", "Even a small completed task deserves recognition."),
            HabitCardModel(14, "Celebrate Wins", "Even a small completed task deserves recognition.")

        )
    )
}
