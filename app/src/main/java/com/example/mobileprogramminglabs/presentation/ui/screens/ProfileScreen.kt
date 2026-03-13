package com.example.mobileprogramminglabs.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.Thistle
import com.example.mobileprogramminglabs.presentation.ui.components.InfoSection
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            title = stringResource(R.string.profile),
            color = DeepTeal
        )
        ProfileSection(
            name = "Ilma",
            levelNo = "3",
            levelDescription = "Building consistency every day"
        )
        InfoSection(
            title = stringResource(R.string.profile_stats),
            rows = listOf(
                InfoRowData("Quests Completed", additionalInfo = "5"),
                InfoRowData("Total XP", additionalInfo = "120"),
                InfoRowData("Achievements", additionalInfo = "3"),
            )
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        InfoSection(
            title = stringResource(R.string.additional),
            rows = listOf(
                InfoRowData("Edit Profile", imageVector = Icons.Default.Edit)
            )
        )
    }
}

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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}