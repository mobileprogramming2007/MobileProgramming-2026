package com.example.mobileprogramminglabs.presentation.ui.screens.habit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlueLight
import com.example.mobileprogramminglabs.presentation.theme.DustyOliveLight
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.theme.Thistle
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.util.HabitCardModel

@Composable
fun HabitCard(
    habit: HabitCardModel,
    modifier: Modifier = Modifier
) {
    val cardColors = listOf(Thistle, RosyTaupe, DustyOliveLight)
    val cardColor = cardColors[habit.id % cardColors.size]

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.size_small)),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.height_small))
        ) {
            if(habit.icon != null) {
                Icon(
                    imageVector = habit.icon,
                    contentDescription = null,
                    tint = AliceBlueLight
                )
            }
            Text(
                text = habit.title,
                style = MaterialTheme.typography.titleMedium,
                color = AliceBlueLight
            )
            Text(
                text = habit.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = AliceBlueLight
            )
        }
    }
}
