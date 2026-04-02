package com.example.mobileprogramminglabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme
import com.example.mobileprogramminglabs.presentation.ui.screens.quest.QuestScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileProgrammingLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->
                    QuestScreen(
                        modifier = Modifier.consumeWindowInsets(innerpadding),)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileProgrammingLabsTheme {
        QuestScreen()
    }
}