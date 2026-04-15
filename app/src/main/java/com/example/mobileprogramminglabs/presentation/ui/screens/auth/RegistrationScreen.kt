package com.example.mobileprogramminglabs.presentation.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.unit.dp
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.components.AuthDivider
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.components.AuthFooterText
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.components.AuthHeader
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.components.AuthPrimaryButton
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.components.AuthTextField
import com.example.mobileprogramminglabs.presentation.ui.components.GoogleIcon
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.components.PasswordTextField
import com.example.mobileprogramminglabs.presentation.ui.screens.auth.components.SocialSignInButton
import com.example.mobileprogramminglabs.presentation.util.AuthValidators

@Composable
fun RegistrationScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val fullNameError = AuthValidators.validateFullName(fullName)
    val emailError = AuthValidators.validateEmail(email)
    val passwordError = AuthValidators.validatePassword(password)
    val confirmPasswordError = AuthValidators.validateConfirmPassword(
        password = password,
        confirmPassword = confirmPassword
    )

    val isRegisterEnabled = fullNameError == null &&
            emailError == null &&
            passwordError == null &&
            confirmPasswordError == null &&
            fullName.isNotBlank() &&
            email.isNotBlank() &&
            password.isNotBlank() &&
            confirmPassword.isNotBlank()

    RegistrationScreen(
        fullName = fullName,
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        passwordVisible = passwordVisible,
        confirmPasswordVisible = confirmPasswordVisible,
        fullNameError = fullNameError,
        emailError = emailError,
        passwordError = passwordError,
        confirmPasswordError = confirmPasswordError,
        isRegisterEnabled = true,
        onFullNameChange = { fullName = it },
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onConfirmPasswordChange = { confirmPassword = it },
        onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
        onConfirmPasswordVisibilityChange = {
            confirmPasswordVisible = !confirmPasswordVisible
        },
        onRegisterClick = onRegisterClick,
        onGoogleClick = { },
        onLoginClick = onLoginClick,
    )
}

@Composable
private fun RegistrationScreen(
    fullName: String,
    email: String,
    password: String,
    confirmPassword: String,
    passwordVisible: Boolean,
    confirmPasswordVisible: Boolean,
    fullNameError: String?,
    emailError: String?,
    passwordError: String?,
    confirmPasswordError: String?,
    isRegisterEnabled: Boolean,
    onFullNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onConfirmPasswordVisibilityChange: () -> Unit,
    onRegisterClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_large)),
        verticalArrangement = Arrangement.Center
    ) {
        AuthHeader(
            title = stringResource(R.string.create_account),
            subtitle = stringResource(R.string.create_acc_subtitle)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        AuthTextField(
            value = fullName,
            onValueChange = onFullNameChange,
            label = "Full Name",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            isError = fullNameError != null,
            errorMessage = fullNameError
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email",
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
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            value = password,
            onValueChange = onPasswordChange,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = onPasswordVisibilityChange,
            label = "Password",
            isError = passwordError != null,
            errorMessage = passwordError
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            passwordVisible = confirmPasswordVisible,
            onPasswordVisibilityChange = onConfirmPasswordVisibilityChange,
            label = "Confirm Password",
            isError = confirmPasswordError != null,
            errorMessage = confirmPasswordError
        )
        Spacer(modifier = Modifier.height(24.dp))
        AuthPrimaryButton(
            text = "Register",
            onClick = onRegisterClick,
            enabled = isRegisterEnabled
        )
        Spacer(modifier = Modifier.height(24.dp))
        AuthDivider()
        Spacer(modifier = Modifier.height(24.dp))
        SocialSignInButton(
            text = "Sign up with Google",
            onClick = onGoogleClick,
            icon = { GoogleIcon() }
        )
        Spacer(modifier = Modifier.height(24.dp))
        AuthFooterText(
            normalText = "Already have an account? ",
            actionText = "Login",
            onActionClick = onLoginClick
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistrationScreenPreview() {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val fullNameError = AuthValidators.validateFullName(fullName)
    val emailError = AuthValidators.validateEmail(email)
    val passwordError = AuthValidators.validatePassword(password)
    val confirmPasswordError = AuthValidators.validateConfirmPassword(
        password = password,
        confirmPassword = confirmPassword
    )

    val isRegisterEnabled = fullNameError == null &&
            emailError == null &&
            passwordError == null &&
            confirmPasswordError == null &&
            fullName.isNotBlank() &&
            email.isNotBlank() &&
            password.isNotBlank() &&
            confirmPassword.isNotBlank()

    RegistrationScreen(
        fullName = fullName,
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        passwordVisible = passwordVisible,
        confirmPasswordVisible = confirmPasswordVisible,
        fullNameError = fullNameError,
        emailError = emailError,
        passwordError = passwordError,
        confirmPasswordError = confirmPasswordError,
        isRegisterEnabled = true,
        onFullNameChange = { fullName = it },
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onConfirmPasswordChange = { confirmPassword = it },
        onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
        onConfirmPasswordVisibilityChange = {
            confirmPasswordVisible = !confirmPasswordVisible
        },
        onRegisterClick = { },
        onGoogleClick = { },
        onLoginClick = { },
    )
}

