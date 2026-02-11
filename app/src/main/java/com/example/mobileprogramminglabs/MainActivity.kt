package com.example.mobileprogramminglabs

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.mobileprogramminglabs.lab2.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task1A()
        task1B()
        task1C()
        task1D()
        task1E()
        printPrimes1To100()

        task2A()
        task2B()
        task2C()
        task2D()
        task2E()

        task3()

        val calc = Calculator()
        Log.d("Task4", "Add: ${calc.add(10.0, 5.0)}")
        Log.d("Task4", "Subtract: ${calc.subtract(10.0, 5.0)}")
        Log.d("Task4", "Multiply: ${calc.multiply(10.0, 5.0)}")
        Log.d("Task4", "Divide: ${calc.divide(10.0, 5.0)}")
        Log.d("Task4", "Divide by 0: ${calc.divide(10.0, 0.0)}")

        val calc2 = Calculator2()
        Log.d("Task4", "Divide 10/3 rounded: ${calc2.divide(10.0, 3.0)}")

        Log.d("Task4", "10 + 5 = ${calculate(10.0, 5.0, "+")}")
        Log.d("Task4", "10 / 0 = ${calculate(10.0, 0.0, "/")}")
        Log.d("Task4", "Invalid op = ${calculate(10.0, 5.0, "%")}")

        val result = calc.add(12.3456, 7.8912)
        Log.d("Task4", "Formatted result: ${result.formatResult()}")
    }
}
