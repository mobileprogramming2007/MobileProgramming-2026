package com.example.mobileprogramminglabs.presentation.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.components.InfoRow
import com.example.mobileprogramminglabs.presentation.ui.components.InfoSection
import com.example.mobileprogramminglabs.presentation.ui.components.RPGButton
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.error.ErrorScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.loading.LoadingScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.profile.components.ProfileSection
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData
import com.example.mobileprogramminglabs.presentation.view_model.profile.ProfileNavigationEvent
import com.example.mobileprogramminglabs.presentation.view_model.profile.ProfileUiState
import com.example.mobileprogramminglabs.presentation.view_model.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onEditNavigate: () -> Unit,
    onLogoutNavigate: () -> Unit,
    onAchievementsClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                ProfileNavigationEvent.Navigate -> onEditNavigate()
                ProfileNavigationEvent.NavigateBack -> onLogoutNavigate()
            }
        }
    }

    when (val state = uiState) {
        is ProfileUiState.Loading -> {
            LoadingScreen()
        }

        is ProfileUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetryClick = { viewModel.resetUiState() }
            )
        }

        is ProfileUiState.Success -> {
            ProfileScreen(
                name = state.profileData.name,
                levelNo = state.profileData.levelNo,
                levelDescription = state.profileData.levelDescription,
                profileStats = state.profileData.profileStats,
                onEditClick = viewModel::onEditClick,
                onLogoutClick = viewModel::onLogoutClick,
                onDeleteClick = viewModel::onDeleteClick,
                onAchievementClick = onAchievementsClick
            )
        }

        is ProfileUiState.Init -> {
            LoadingScreen()
        }
    }
}

@Composable
private fun ProfileScreen(
    name: String,
    levelNo: String,
    levelDescription: String,
    profileStats: List<InfoRowData>,
    onEditClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onAchievementClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            name = name,
            levelNo = levelNo,
            levelDescription = levelDescription
        )
        InfoSection(
            title = stringResource(R.string.profile_stats),
            rows = profileStats
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.padding_medium)
            )
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onEditClick)
            ) {
                InfoRow(
                    title = "Edit Profile",
                    imageVector = Icons.Default.Edit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DeepTeal)
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onDeleteClick)
            ) {
                InfoRow(
                    title = "Delete Profile",
                    imageVector = Icons.Default.Delete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DeepTeal)
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onAchievementClick)
        ) {
            InfoRow(
                title = "Achievements",
                imageVector = Icons.Default.CheckCircle,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DeepTeal)
                    .padding(dimensionResource(R.dimen.padding_medium))
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        RPGButton(
            title = "Logout",
            enabled = true,
            onButtonClick = onLogoutClick
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen(
            name = "Ilma",
            levelNo = "3",
            levelDescription = "Building consistency every day",
            profileStats = listOf(
                InfoRowData("Quests Completed", additionalInfo = "5"),
                InfoRowData("Total XP", additionalInfo = "120"),
                InfoRowData("Achievements", additionalInfo = "Top")
            ),
            onEditClick = {},
            onLogoutClick = {},
            onDeleteClick = {},
            onAchievementClick = {}
        )
    }
}
