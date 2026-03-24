package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTealDark

@Composable
fun GoogleIcon() {
    Icon(
        painter = painterResource(R.drawable.google_icon),
        contentDescription = "Google",
        tint = DeepTealDark
    )
}

@Composable
fun AppleIcon() {
    Icon(
        painter = painterResource(R.drawable.apple__streamline_unicons),
        contentDescription = "Apple",
        tint = DeepTealDark
    )
}
