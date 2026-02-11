package com.example.mobileprogramminglabs.lab2

import android.util.Log

data class Student(
    val name: String,
    val id: Int,
    val grades: List<Int>
)

data class Student2(
    val name: String,
    val id: Int,
    val grades: List<Int>
) {
    fun average(): Double = grades.average()

    fun letterGrade(): String {
        val avg = average()
        return when {
            avg >= 90 -> "A"
            avg >= 80 -> "B"
            avg >= 70 -> "C"
            avg >= 60 -> "D"
            else -> "F"
        }
    }
}

fun Student2.isPassing(): Boolean = this.average() >= 55

fun task3() {
    val students = listOf(
        Student2("Amina", 1, listOf(90, 92, 88)),
        Student2("Kenan", 2, listOf(70, 75, 72)),
        Student2("Sara", 3, listOf(85, 80, 83)),
        Student2("Dino", 4, listOf(60, 64, 58)),
        Student2("Lejla", 5, listOf(95, 98, 93)),
        Student2("Tarik", 6, listOf(40, 55, 50)),
        Student2("Nina", 7, listOf(78, 81, 77)),
        Student2("Haris", 8, listOf(88, 86, 90)),
        Student2("Ajla", 9, listOf(69, 70, 71)),
        Student2("Adnan", 10, listOf(82, 79, 85))
    )

    val top3 = students
        .sortedByDescending { it.average() }
        .take(3)

    Log.d("Task3","Top 3 Students")
    top3.forEach {
        Log.d(
            "Task3",
            "${it.name} (${it.id}) -> avg=${"%.2f".format(it.average())}, grade=${it.letterGrade()}"
        )    }

    Log.d("Task3", "\nPassing Students")
    students.filter { it.isPassing() }
        .forEach {
            Log.d(
                "Task3",
                "${it.name} -> ${"%.2f".format(it.average())}"
            )
        }
}

