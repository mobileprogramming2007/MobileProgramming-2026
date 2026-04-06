package com.example.mobileprogramminglabs.presentation.ui.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.theme.Thistle

@Composable
fun UserSectionCard(
    levelNo: Int,
    achievementLevel: String,
    currentXP: Int,
    maxXP: Int,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = modifier
            .background(DeepTeal)
            .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = modifier
                        .size(dimensionResource(R.dimen.avatar_size))
                        .clip(CircleShape)
                        .background(AliceBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(R.string.player_avatar),
                        modifier = modifier.size(dimensionResource(R.dimen.icon_size_medium)),
                        tint = DeepTeal
                    )
                }
                Spacer(modifier = modifier.width(dimensionResource(R.dimen.width_medium)))
                Column {
                    Text(
                        text = "Level $levelNo $achievementLevel",
                        style = MaterialTheme.typography.titleMedium,
                        color = Thistle
                    )
                    Text(
                        text = "XP: $currentXP / $maxXP",
                        style = MaterialTheme.typography.bodyMedium,
                        color = AliceBlue
                    )
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
            LinearProgressIndicator(
                progress = { currentXP.toFloat() / maxXP.toFloat() },
                color = AliceBlue,
                trackColor = RosyTaupe,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.height_small))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserSectionCardPreview() {
    MaterialTheme {
        UserSectionCard(levelNo = 5, achievementLevel = "120", currentXP = 120, maxXP = 200)
    }
}
