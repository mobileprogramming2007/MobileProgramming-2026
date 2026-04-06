package com.example.mobileprogramminglabs.presentation.ui.screens.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTealDark
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupeBeige

@Composable
fun PasswordTextField(
    value: String,
    label: String,
    passwordVisible: Boolean,
    onValueChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            singleLine = true,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = DeepTealDark
                )
            },
            trailingIcon = {
                Text(
                    text = if (passwordVisible) "Hide" else "Show",
                    color = DeepTealDark,
                    modifier = Modifier
                        .clickable { onPasswordVisibilityChange() }
                        .padding(end = dimensionResource(R.dimen.padding_xsmedium))
                )
            },
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            shape = RoundedCornerShape(dimensionResource(R.dimen.size_small)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DeepTealDark,
                unfocusedBorderColor = RosyTaupeBeige,
                focusedLabelColor = DeepTealDark,
                cursorColor = DeepTealDark,
                errorBorderColor = Color.Red,
                errorLabelColor = Color.Red,
                errorCursorColor = Color.Red
            )
        )

        if (isError && !errorMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_small)))
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

