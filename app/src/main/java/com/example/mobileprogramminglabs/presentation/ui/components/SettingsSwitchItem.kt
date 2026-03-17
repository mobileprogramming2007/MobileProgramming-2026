package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.DustyOlive
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.theme.Thistle

@Composable
fun SettingsSwitchItem(
    icon: ImageVector,
    title: String,
    checked: Boolean,
    color: Color = AliceBlue,
    iconColor: Color = RosyTaupe,
    titleColor: Color = DustyOlive,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.size_small)))
            .background(color)
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = AliceBlue,
                checkedTrackColor = DeepTeal,
                checkedBorderColor = DeepTeal,
                uncheckedThumbColor = Thistle,
                uncheckedTrackColor = RosyTaupe,
                uncheckedBorderColor = RosyTaupe
            )
        )
    }
}
