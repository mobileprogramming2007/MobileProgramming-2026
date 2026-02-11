package com.example.mobileprogramminglabs.lab2

class Calculator {
    fun add(a: Double, b: Double) = a + b
    fun subtract(a: Double, b: Double) = a - b
    fun multiply(a: Double, b: Double) = a * b

    fun divide(a: Double, b: Double): Double? {
        return if (b == 0.0) null else a / b
    }
}

class Calculator2 {
    fun add(a: Double, b: Double) = a + b
    fun subtract(a: Double, b: Double) = a - b
    fun multiply(a: Double, b: Double) = a * b

    fun divide(a: Double, b: Double): Double? {
        if (b == 0.0) return null
        return safeRound(a / b)
    }

    private fun safeRound(x: Double): Double {
        // Example helper: round to 2 decimals internally
        return kotlin.math.round(x * 100) / 100
    }
}

fun calculate(a: Double, b: Double, op: String): Double? {
    return when (op) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> if (b == 0.0) null else a / b
        else -> null
    }
}

fun Double.formatResult(): String = String.format("%.2f", this)

