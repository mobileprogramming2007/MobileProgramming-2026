package com.example.mobileprogramminglabs.presentation.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.components.InfoSection
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.error.ErrorScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.loading.LoadingScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.profile.components.ProfileSection
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData
import com.example.mobileprogramminglabs.presentation.view_model.profile.ProfileUiState
import com.example.mobileprogramminglabs.presentation.view_model.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                additionalRows = state.profileData.additionalRows
            )
        }

        else -> {
            //no-op
        }
    }
}

@Composable
fun ProfileScreen(
    name: String,
    levelNo: String,
    levelDescription: String,
    profileStats: List<InfoRowData>,
    additionalRows: List<InfoRowData>,
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
        InfoSection(
            title = stringResource(R.string.additional),
            rows = additionalRows
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
            additionalRows = listOf(
                InfoRowData("Edit Profile", imageVector = Icons.Default.Edit)
            )
        )
    }
}
