package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupeBeige

@Composable
fun AuthDivider(
    modifier: Modifier = Modifier,
    text: String = "OR"
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(dimensionResource(R.dimen.height_xxssmall))
                .background(RosyTaupeBeige.copy(alpha = 0.4f))
        )
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_xsmedium)),
            color = RosyTaupeBeige,
            style = MaterialTheme.typography.bodyMedium
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(dimensionResource(R.dimen.height_xxssmall))
                .background(RosyTaupeBeige.copy(alpha = 0.4f))
        )
    }
}