package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.DustyOlive
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe

@Composable
fun DifficultyOption(
    label: String,
    onDifficultyOptionClick: () -> Unit,
    selected: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selected,
            onClick = onDifficultyOptionClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = RosyTaupe,
                unselectedColor = DustyOlive
            )
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.width_xmedium)))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = DeepTeal
        )
    }
}
