package com.example.mobileprogramminglabs

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.lab2.KotlinBasics
import com.example.mobileprogramminglabs.ui.theme.MobileProgrammingLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {}

        Log.d("Lab 2 - TASK 1", "-----------------------------")
        val kotlinBasics = KotlinBasics()

        kotlinBasics.taskA()
        kotlinBasics.taskB()
        kotlinBasics.taskC()
        kotlinBasics.taskD()
        kotlinBasics.taskE()
        kotlinBasics.printPrimes1To100()

        Log.d("Lab 2 - TASK 1", "DONE")
    }
}
