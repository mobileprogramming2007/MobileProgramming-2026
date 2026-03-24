package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe

@Composable
fun HabitItem(
    title: String,
    streak: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.size_small)))
            .background(AliceBlue)
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = title,
                tint = RosyTaupe,
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size))
            )
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_xsmall)))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = DeepTeal,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = streak,
            style = MaterialTheme.typography.bodyMedium,
            color = DeepTeal
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HabitItemPreview() {
    MaterialTheme {
        HabitItem(
            title = "Quests Completed",
            streak = "5"
        )
    }
}
