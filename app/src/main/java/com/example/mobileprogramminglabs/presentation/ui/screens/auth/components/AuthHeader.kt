package com.example.mobileprogramminglabs.presentation.ui.screens.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTealDark
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupeBeige

@Composable
fun AuthHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = DeepTealDark
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_small)))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = RosyTaupeBeige
        )
    }
}

@Preview
@Composable
private fun AuthHeaderPreview() {
    MobileProgrammingLabsTheme {
        AuthHeader(title = "Header", subtitle = "Header Subtitle")
    }
}