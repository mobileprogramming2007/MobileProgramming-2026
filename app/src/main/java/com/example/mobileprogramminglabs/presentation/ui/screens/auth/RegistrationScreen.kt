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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun RegistrationScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        AuthHeader(
            title = "Create Account",
            subtitle = "Register and start building your journey"
        )
        Spacer(modifier = Modifier.height(24.dp))
        AuthTextField(
            value = "",
            onValueChange = {},
            label = "Full Name",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            isError = false,
            errorMessage = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthTextField(
            value = "",
            onValueChange = {} ,
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
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            value = "",
            onValueChange = {},
            label = "Password",
            isError = false,
            errorMessage = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            value = "",
            onValueChange = {},
            label = "Confirm Password",
            isError = false,
            errorMessage = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        AuthPrimaryButton(
            text = "Register",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(24.dp))
        AuthDivider()
        Spacer(modifier = Modifier.height(24.dp))
        SocialSignInButton(
            text = "Sign up with Google",
            onClick = { },
            icon = { GoogleIcon() }
        )
        Spacer(modifier = Modifier.height(12.dp))
        SocialSignInButton(
            text = "Sign up with Apple",
            onClick = { },
            icon = { AppleIcon() }
        )
        Spacer(modifier = Modifier.height(24.dp))
        AuthFooterText(
            normalText = "Already have an account? ",
            actionText = "Login",
            onActionClick = { }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen()
}

