package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.DustyOlive
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme

@Composable
fun RPGButton(
    title: String,
    enabled: Boolean,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onButtonClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = DeepTeal,
            disabledContainerColor = DustyOlive
        )
    ) {
        Text(
            text = title,
            color = AliceBlue
        )
    }
}

@Preview
@Composable
private fun RPGButtonPrev() {
    MobileProgrammingLabsTheme{
        RPGButton(
            title = "Save Quest",
            enabled = true,
            onButtonClick = {}
        )
    }
}