package com.example.mobileprogramminglabs.presentation.ui.screens.motivation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.AliceBlueLight
import com.example.mobileprogramminglabs.presentation.theme.DeepTealDark
import com.example.mobileprogramminglabs.presentation.theme.DustyOliveLight
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.theme.Thistle
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.motivation.util.MotivationCardModel


@Composable
fun MotivationWallScreen(
    cards: List<MotivationCardModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AliceBlue)
            .padding(vertical = dimensionResource(R.dimen.padding_medium))
    ) {
        Title(
            title = "Motivation Wall",
            color = DeepTealDark,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )

        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = dimensionResource(R.dimen.padding_medium)),
            contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.padding_medium)),
            horizontalItemSpacing = dimensionResource(R.dimen.padding_medium),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            items(
                items = cards,
                key = { card -> card.id }
            ) { card ->
                MotivationWallCard(card = card)
            }
        }
    }
}

@Composable
fun MotivationWallCard(
    card: MotivationCardModel,
    modifier: Modifier = Modifier
) {
    val colors = listOf(Thistle, RosyTaupe, DustyOliveLight)
    val background = colors[card.id % colors.size]

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.size_small)),
        colors = CardDefaults.cardColors(containerColor = background)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = card.title,
                style = MaterialTheme.typography.titleMedium,
                color = AliceBlueLight
            )
            Text(
                text = card.message,
                style = MaterialTheme.typography.bodyMedium,
                color = AliceBlueLight
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MotivationWallScreenPreview() {
    MotivationWallScreen(
        cards = listOf(
            MotivationCardModel(1, "Keep Moving", "Even one completed task today is meaningful progress."),
            MotivationCardModel(2, "Focus", "Finish the next small step before thinking about the whole journey."),
            MotivationCardModel(3, "Patience", "Growth can be quiet and still be real."),
            MotivationCardModel(4, "Discipline", "What you repeat daily becomes your result."),
            MotivationCardModel(5, "Reset", "A difficult day is not a failed week."),
            MotivationCardModel(6, "Balance", "Rest and effort work best together.")
        )
    )
}