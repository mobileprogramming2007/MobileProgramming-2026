package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTealDark
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupeBeige

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            leadingIcon = leadingIcon,
            singleLine = true,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
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
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_xsmall)))
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
private fun AuthTextFieldPrev() {
    MobileProgrammingLabsTheme() {
        AuthTextField(
            value= "",
            onValueChange={},
            label="",
            leadingIcon = null
        )
    }

}
