package com.example.mobileprogramminglabs.presentation.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal

@Composable
fun DeleteQuestDialog(questTitle: String) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(questTitle) },
        text = { Text("Are you sure you want to remove \"$questTitle\"?") },
        confirmButton = {
            TextButton(onClick = {}) {
                Text(
                    text = "Delete",
                    color = AliceBlue
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {}) {
                Text(
                    text = "Cancel",
                    color = AliceBlue
                )
            }
        },
        containerColor = DeepTeal,
        titleContentColor = AliceBlue,
        textContentColor = AliceBlue

    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeleteQuestPreview() {
    MaterialTheme {
        DeleteQuestDialog(questTitle = "Delete Quest")
    }
}