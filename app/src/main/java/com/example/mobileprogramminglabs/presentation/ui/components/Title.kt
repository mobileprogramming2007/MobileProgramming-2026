package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe

@Composable
fun Title(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        color = RosyTaupe,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))
    )
}

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    MaterialTheme {
        Title(title = "LifeRPG")
    }
}
