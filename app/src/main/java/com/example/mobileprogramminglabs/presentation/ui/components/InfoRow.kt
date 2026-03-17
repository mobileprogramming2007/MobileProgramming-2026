package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe

@Composable
fun InfoRow(
    title: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    additionalInfo: String? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.padding_xsmall)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (imageVector != null) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = RosyTaupe,
                    modifier = Modifier.size(dimensionResource(R.dimen.icon_size))
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.width_small)))
            }
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = AliceBlue
            )
        }
        if (additionalInfo != null) {
            Text(
                text = additionalInfo,
                style = MaterialTheme.typography.bodyLarge,
                color = RosyTaupe
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InfoRowPreview() {
    MaterialTheme {
        InfoRow(
            title = "Quests Completed",
            additionalInfo = "5"
        )
    }
}
