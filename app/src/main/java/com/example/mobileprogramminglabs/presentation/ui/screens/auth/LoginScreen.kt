package com.example.mobileprogramminglabs.presentation.ui.screens.auth

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.ui.components.AppleIcon
import com.example.mobileprogramminglabs.presentation.ui.components.AuthDivider
import com.example.mobileprogramminglabs.presentation.ui.components.AuthFooterText
import com.example.mobileprogramminglabs.presentation.ui.components.AuthHeader
import com.example.mobileprogramminglabs.presentation.ui.components.AuthPrimaryButton
import com.example.mobileprogramminglabs.presentation.ui.components.AuthTextField
import com.example.mobileprogramminglabs.presentation.ui.components.GoogleIcon
import com.example.mobileprogramminglabs.presentation.ui.components.PasswordTextField
import com.example.mobileprogramminglabs.presentation.ui.components.SocialSignInButton

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_large)),
        verticalArrangement = Arrangement.Center
    ) {
        AuthHeader(
            title = "Welcome Back",
            subtitle = "Login to continue your journey"
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        AuthTextField(
            value = "",
            onValueChange = {},
            label = "Email",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            isError = false,
            errorMessage = null
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_medium)))
        PasswordTextField(
            value = "",
            onValueChange = {},
            label = "Password",
            isError = false,
            errorMessage = null
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        AuthPrimaryButton(
            text = "Login",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        AuthDivider()
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        SocialSignInButton(
            text = "Continue with Google",
            onClick = { },
            icon = { GoogleIcon() }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xmedium)))
        SocialSignInButton(
            text = "Continue with Apple",
            onClick = { },
            icon = { AppleIcon() }
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_large)))
        AuthFooterText(
            normalText = "Don’t have an account? ",
            actionText = "Register",
            onActionClick = { }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}