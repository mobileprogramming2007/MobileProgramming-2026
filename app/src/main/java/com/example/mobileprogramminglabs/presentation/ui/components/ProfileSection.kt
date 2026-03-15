package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.Thistle

@Composable
fun ProfileSection(
    name: String,
    levelNo: String,
    levelDescription: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(dimensionResource(R.dimen.avatar_large))
            .clip(CircleShape)
            .background(Thistle),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = stringResource(R.string.profile_avatar),
            modifier = Modifier.size(40.dp)
        )
    }
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
    Text(
        text = "$name Player",
        style = MaterialTheme.typography.titleLarge,
        color = DeepTeal,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = "Level $levelNo Adventurer",
        style = MaterialTheme.typography.bodyLarge,
        color = DeepTeal,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = levelDescription,
        style = MaterialTheme.typography.bodyMedium,
        color = DeepTeal,
        fontStyle = FontStyle.Italic
    )
    Spacer(modifier = Modifier.height(24.dp))

}