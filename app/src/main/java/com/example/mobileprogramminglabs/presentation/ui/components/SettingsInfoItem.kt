package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DustyOlive
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe

@Composable
fun SettingsInfoItem(
    icon: ImageVector,
    title: String,
    color: Color = AliceBlue,
    iconColor: Color = RosyTaupe,
    titleColor: Color = DustyOlive
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.size_small)))
            .background(color)
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = iconColor
        )
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_xsmall)))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = titleColor,
            fontWeight = FontWeight.Bold
        )
    }
}
