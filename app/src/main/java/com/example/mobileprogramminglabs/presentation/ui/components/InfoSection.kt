package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import kotlin.collections.forEach
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData

@Composable
fun InfoSection(
    title: String,
    rows: List<InfoRowData>,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = modifier
            .background(color = DeepTeal)
            .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = AliceBlue
            )
            Spacer(modifier = modifier.height(dimensionResource(R.dimen.height_xmedium)))
            rows.forEach { row ->
                InfoRow(
                    title = row.title,
                    additionalInfo = row.additionalInfo,
                    imageVector = row.imageVector
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InfoSectionPreview() {
    MaterialTheme {
        InfoSection(
            title = "Quick Stats",
            rows = listOf(
                InfoRowData(
                    title = "Quests Completed",
                    additionalInfo = "5"
                ),
                InfoRowData(
                    title = "Total XP",
                    additionalInfo = "120"
                ),
                InfoRowData(
                    title = "Achievements",
                    additionalInfo = "3"
                )
            )
        )
    }
}
