package com.example.mobileprogramminglabs.presentation.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.mobileprogramminglabs.presentation.theme.DeepTealDark
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupeBeige

@Composable
fun AuthFooterText(
    normalText: String,
    actionText: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = normalText,
            color = RosyTaupeBeige
        )
        Text(
            text = actionText,
            color = DeepTealDark,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(onClick = onActionClick)
        )
    }
}
