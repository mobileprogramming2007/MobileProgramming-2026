package com.example.mobileprogramminglabs.lab2

import android.util.Log

class KotlinBasics {
    fun taskA() {
        val university = "IBU"
        val city = "Sarajevo"
        val year = 2026
        // university = "MIT" //'val' cannot be reassigned - compile error

        var course = "Mobile Programming"
        var level = 1
        var isActive = true

        course = "Introduction to Mobile Programming"
        level = 2
        isActive = false

        Log.d("KotlinBasics A", "Course=$course - Level=$level - IsActive=$isActive")
    }

    fun taskB() {
        //Type inference (no explicit type)
        val name = "Mirza"
        val age = 27
        val gpa = 9.8

        //Explicit types
        val isStudent: Boolean = true
        val gradeLetter: Char = 'A' //single '' will only work with chars

        Log.d("KotlinBasics B", "Name: $name, Age: $age, GPA: $gpa, Active: $isStudent, Grade: $gradeLetter")
    }

    fun taskC() {
        var nickname: String? = "Ajla"

        Log.d("KotlinBasics C", "Safe-call Length: ${nickname?.length}") //safe-call
        Log.d("KotlinBasics C", "Elvis Length: ${nickname?.length ?: 0}") //elvis operator
        Log.d("KotlinBasics C", "!! length (before null): ${nickname!!.length}")

        nickname = null

        Log.d("KotlinBasics C", "Safe-call Length (after null): ${nickname?.length}")
        Log.d("KotlinBasics C", "Elvis Length (after null): ${nickname?.length ?: 0}")
        //Log.d("KotlinBasics", "!! length (after null): ${nickname!!.length}") //NullPointerException
    }

    fun calculateGrade(score: Int): String {
        return when (score) {
            in 90..100 -> "A"
            in 80..89 -> "B"
            in 70..79 -> "C"
            in 60..69 -> "D"
            in 0..59 -> "F"
            else -> "Invalid"
        }
    }

    fun taskD() {
        Log.d("KotlinBasics D", "95 -> ${calculateGrade(95)}")
        Log.d("KotlinBasics D", "72 -> ${calculateGrade(72)}")
        Log.d("KotlinBasics D", "40 -> ${calculateGrade(40)}")
    }

    fun taskE() {
        for (i in 1..100) {
            val out = when {
                i % 15 == 0 -> "FizzBuzz"
                i % 3 == 0 -> "Fizz"
                i % 5 == 0 -> "Buzz"
                else -> i.toString()
            }
            Log.d("KotlinBasics E", out)
        }
    }

    fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2..kotlin.math.sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }

    fun printPrimes1To100() {
        for (i in 1..100) if (isPrime(i)) println(i)
    }
}
