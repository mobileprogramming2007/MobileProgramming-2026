package com.example.mobileprogramminglabs.presentation.ui.screens.achievement.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.ui.screens.achievement.util.AchievementData

@Composable
fun AchievementItem(
    achievement: AchievementData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.padding_medium)
            )
        ) {
            Image(
                painter = painterResource(id = achievement.iconRes),
                contentDescription = achievement.title,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.height_large))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.size_small)))
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = achievement.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = DeepTeal
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_small)))

                Text(
                    text = "XP Bonus: ${achievement.xpBonus}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = achievement.conditionType,
                    style = MaterialTheme.typography.bodyMedium
                )

                if (achievement.isUnlocked) {
                    Text(
                        text = "Unlocked: ${achievement.unlockedAt.orEmpty()}",
                        style = MaterialTheme.typography.bodySmall,
                        color = DeepTeal
                    )
                } else {
                    Text(
                        text = "Locked",
                        style = MaterialTheme.typography.bodySmall,
                        color = RosyTaupe
                    )
                }
            }
        }
    }
}
