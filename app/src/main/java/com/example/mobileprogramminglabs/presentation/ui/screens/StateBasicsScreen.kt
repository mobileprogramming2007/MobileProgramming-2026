package com.example.mobileprogramminglabs.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme

@Composable
fun StateBasicsScreen() {

    // Counter state (will reset on rotation)
    var counter by remember { mutableIntStateOf(0) }

    // Text input (will survive rotation)
    var text by rememberSaveable { mutableStateOf("") }

    // Checkbox state
    var isChecked by remember { mutableStateOf(false) }

    // Derived state (recomputes when counter changes)
    val doubleCounter by remember {
        derivedStateOf { counter * 2 }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "State Basics Practice",
            style = MaterialTheme.typography.titleLarge
        )

        //Counter Section
        Text(text = "Counter: $counter")

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { counter-- }) {
                Text("-")
            }
            Button(onClick = { counter++ }) {
                Text("+")
            }
        }

        //Derived State
        Text(text = "Double Counter: $doubleCounter")

        HorizontalDivider()

        //Text Input Section
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter something") }
        )

        Text(text = "You typed: $text")

        HorizontalDivider()

        //Checkbox Section
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Text(text = "Check me")
        }

        Text(text = if (isChecked) "Checked" else "Not checked")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StateBasicsScreenPreview() {
    MobileProgrammingLabsTheme {
        StateBasicsScreen()
    }
}
