package com.example.mobileprogramminglabs.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme
import com.example.mobileprogramminglabs.presentation.ui.components.AppleIcon
import com.example.mobileprogramminglabs.presentation.ui.components.AuthDivider
import com.example.mobileprogramminglabs.presentation.ui.components.AuthFooterText
import com.example.mobileprogramminglabs.presentation.ui.components.AuthHeader
import com.example.mobileprogramminglabs.presentation.ui.components.AuthPrimaryButton
import com.example.mobileprogramminglabs.presentation.ui.components.AuthTextField
import com.example.mobileprogramminglabs.presentation.ui.components.GoogleIcon
import com.example.mobileprogramminglabs.presentation.ui.components.PasswordTextField
import com.example.mobileprogramminglabs.presentation.ui.components.SocialSignInButton
import com.example.mobileprogramminglabs.presentation.util.AuthValidators

@Composable
fun LoginScreen() {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val emailError = AuthValidators.validateEmail(email)
    val passwordError = AuthValidators.validatePassword(password)

    val isLoginEnabled = emailError == null &&
            passwordError == null &&
            email.isNotBlank() &&
            password.isNotBlank()

    LoginScreen(
        email = email,
        password = password,
        passwordVisible = passwordVisible,
        emailError = emailError,
        passwordError = passwordError,
        isLoginEnabled = isLoginEnabled,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onPasswordVisibilityChange = {
            passwordVisible = !passwordVisible
        },
        onLoginClick = { },
        onRegisterClick = { },
        onGoogleClick = { },
        onAppleClick = { }
    )
}
@Composable
private fun LoginScreen(
    email: String,
    password: String,
    emailError: String?,
    passwordError: String?,
    isLoginEnabled: Boolean,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_large)),
        verticalArrangement = Arrangement.Center
    ) {
        AuthHeader(
            title = stringResource(R.string.welcome_back),
            subtitle = stringResource(R.string.login_subtitle)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        AuthTextField(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.email),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            keyboardType = KeyboardType.Email,
            isError = emailError != null,
            errorMessage = emailError
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        PasswordTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = stringResource(R.string.password),
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = onPasswordVisibilityChange,
            isError = passwordError != null,
            errorMessage = passwordError
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        AuthPrimaryButton(
            text = stringResource(R.string.login),
            onClick = onLoginClick,
            enabled = isLoginEnabled
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        AuthDivider()
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        SocialSignInButton(
            text = stringResource(R.string.continue_with_google),
            onClick = onGoogleClick,
            icon = { GoogleIcon() }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xmedium)))
        SocialSignInButton(
            text = stringResource(R.string.continue_with_apple),
            onClick = onAppleClick,
            icon = { AppleIcon() }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        AuthFooterText(
            normalText = stringResource(R.string.don_not_have_account),
            actionText = stringResource(R.string.register),
            onActionClick = onRegisterClick
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MobileProgrammingLabsTheme() {
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        val emailError = AuthValidators.validateEmail(email)
        val passwordError = AuthValidators.validatePassword(password)

        val isLoginEnabled = emailError == null &&
                passwordError == null &&
                email.isNotBlank() &&
                password.isNotBlank()

        LoginScreen(
            email = email,
            password = password,
            passwordVisible = passwordVisible,
            emailError = emailError,
            passwordError = passwordError,
            isLoginEnabled = isLoginEnabled,
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onPasswordVisibilityChange = {
                passwordVisible = !passwordVisible
            },
            onLoginClick = { },
            onRegisterClick = { },
            onGoogleClick = { },
            onAppleClick = { }
        )
    }
}
