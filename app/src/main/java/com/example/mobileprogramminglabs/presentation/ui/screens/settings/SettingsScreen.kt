package com.example.mobileprogramminglabs.presentation.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.screens.settings.components.SettingsInfoItem
import com.example.mobileprogramminglabs.presentation.ui.screens.settings.components.SettingsSwitchItem
import com.example.mobileprogramminglabs.presentation.ui.components.Title

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Title(
            title = stringResource(R.string.settings),
            color = DeepTeal,
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_medium))
        )
        SettingsSwitchItem(
            icon = Icons.Default.Notifications,
            title = "Notifications",
            checked = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xmedium)))
        SettingsSwitchItem(
            icon = Icons.Default.Settings,
            title = "Dark Mode",
            checked = darkModeEnabled,
            onCheckedChange = { darkModeEnabled = it }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xmedium)))
        SettingsInfoItem(
            icon = Icons.Default.Person,
            title = "Account"
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xmedium)))
        SettingsInfoItem(
            icon = Icons.Default.Info,
            title = "Help & Support"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreen()
    }
}
