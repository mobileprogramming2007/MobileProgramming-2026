package com.example.mobileprogramminglabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileProgrammingLabsTheme {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileProgrammingLabsTheme {
        //QuestScreen()
    }
}