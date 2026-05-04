package com.example.mobileprogramminglabs.presentation.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.ui.components.RPGButton
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.error.ErrorScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.loading.LoadingScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.profile.components.ProfileTextField
import com.example.mobileprogramminglabs.presentation.view_model.profile.edit_profile.EditProfileNavigationEvent
import com.example.mobileprogramminglabs.presentation.view_model.profile.edit_profile.EditProfileUiState
import com.example.mobileprogramminglabs.presentation.view_model.profile.edit_profile.EditProfileViewModel

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val formState = viewModel.formState

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                EditProfileNavigationEvent.Navigate -> Unit
                EditProfileNavigationEvent.NavigateBack -> onNavigateBack()
            }
        }
    }

    when (val state = uiState) {
        EditProfileUiState.Loading -> {
            LoadingScreen()
        }

        is EditProfileUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetryClick = { viewModel.resetUiState() }
            )
        }

        else -> {
            EditProfileScreen(
                fullName = formState.fullName,
                email = formState.email,
                password = formState.password,
                confirmPassword = formState.confirmPassword,
                onFullNameChange = viewModel::onFullNameChange,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
                onSaveClick = viewModel::onSaveClick
            )
        }
    }
}

@Composable
private fun EditProfileScreen(
    fullName: String,
    password: String,
    email: String,
    confirmPassword: String,
    onFullNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isSaveEnabled = fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Title(
            title = "Edit Profile",
            color = DeepTeal
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        ProfileTextField(
            value = fullName,
            onValueChange = onFullNameChange,
            label = "Full Name"
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        ProfileTextField(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.email)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        ProfileTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Password"
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        ProfileTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = "Confirm Password"
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        RPGButton(
            title = "Save",
            enabled = isSaveEnabled,
            onButtonClick = onSaveClick
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfileScreenPreview() {
    MaterialTheme {
        EditProfileScreen(
            fullName = "Ilma",
            password = "1234",
            email = "1@gmail.com",
            confirmPassword = "1234",
            onFullNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onSaveClick = {},
            onConfirmPasswordChange = {}
        )
    }
}