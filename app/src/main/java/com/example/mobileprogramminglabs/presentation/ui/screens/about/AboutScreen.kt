package com.example.mobileprogramminglabs.presentation.ui.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.components.Title

@Composable
fun AboutScreen() {
    val version = stringResource(R.string.version)
    val description = stringResource(R.string.about_app_description)
    val support = stringResource(R.string.support)

    AboutScreen(
        version = version,
        description = description,
        support = support
    )
}

@Composable
private fun AboutScreen(
    version: String,
    description: String,
    support: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Title(
            title = stringResource(R.string.about_app),
            color = DeepTeal,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensionResource(R.dimen.size_small)))
                .background(DeepTeal)
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(R.string.app_title),
                style = MaterialTheme.typography.titleLarge,
                color = AliceBlue,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_small)))
            Text(
                text = version,
                style = MaterialTheme.typography.bodyMedium,
                color = AliceBlue,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xmedium)))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = AliceBlue
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xmedium)))
            Text(
                text = support,
                style = MaterialTheme.typography.bodyMedium,
                color = AliceBlue,
                fontWeight = FontWeight.W800
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    MaterialTheme {
        AboutScreen(
            version = "Version 1.0.0",
            description = "This application helps users track habits, stay mindful, and improve daily well-being.",
            support = "Support: support@mindfulmate.com"
        )
    }
}
