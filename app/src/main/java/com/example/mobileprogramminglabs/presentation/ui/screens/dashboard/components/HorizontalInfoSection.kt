package com.example.mobileprogramminglabs.presentation.ui.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.components.InfoRow
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData

@Composable
fun HorizontalInfoSection(
    title: String,
    rows: List<InfoRowData>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DeepTeal
            )
    ) {
        Column(
            modifier = Modifier
                .background(color = DeepTeal)
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = AliceBlue
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xmedium)))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.padding_small)
                )
            ) {
                items(rows) { row ->
                    InfoRow(
                        title = row.title,
                        additionalInfo = row.additionalInfo,
                        imageVector = row.imageVector
                    )
                }
            }
        }
    }
}