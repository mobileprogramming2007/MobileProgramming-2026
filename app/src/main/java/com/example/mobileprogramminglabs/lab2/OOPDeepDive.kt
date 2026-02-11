package com.example.mobileprogramminglabs.lab2

import android.util.Log

class Person(val name: String, val age: Int) {

    // Secondary constructor
    constructor(name: String) : this(name = name, age = 18)
}

fun task2A() {
    val p1 = Person(name = "Amina", age = 22)
    val p2 = Person(name = "Kenan")

    Log.d("Task A", "${p1.name} ${p1.age}")
    Log.d("Task A", "${p2.name} ${p2.age}")
}

class PersonWithGetter(val name: String, val age: Int) {
    val isAdult: Boolean
        get() = age >= 18
}

fun task2B() {
    val a = PersonWithGetter(name = "Ali", age = 17)
    val b = PersonWithGetter(name = "Sara", age = 19)

    Log.d("Task B","${a.name} adult? ${a.isAdult}")
    Log.d("Task B","${b.name} adult? ${b.isAdult}")
}

class BankAccount(initialBalance: Double) {
    var balance: Double = initialBalance
        set(value) {
            if (value < 0) {
                Log.d("Task C", "Invalid balance: cannot be negative ($value). Keeping old balance=$field")
            } else {
                field = value
            }
        }
}

fun task2C() {
    val acc = BankAccount(100.0)
    Log.d("Task C","Start balance: ${acc.balance}")

    acc.balance = 200.0
    Log.d("Task C","Updated balance: ${acc.balance}")

    acc.balance = -50.0 // should be rejected
    Log.d("Task C","After invalid set: ${acc.balance}")
}

class SecureBankAccount(initialBalance: Double) {
    private var balance: Double = initialBalance

    fun deposit(amount: Double) {
        if (amount <= 0) {
            println("Deposit must be positive.")
            return
        }
        balance += amount
    }

    fun withdraw(amount: Double) {
        if (amount <= 0) {
            println("Withdraw must be positive.")
            return
        }
        if (amount > balance) {
            println("Not enough funds.")
            return
        }
        balance -= amount
    }

    fun getBalance(): Double = balance
}

fun task2D() {
    val acc = SecureBankAccount(100.0)

    // println(acc.balance) // compile error (private)
    acc.deposit(50.0)
    acc.withdraw(30.0)

    Log.d("Task D","Balance via method: ${acc.getBalance()}")
}

open class Vehicle(val brand: String) {
    open fun description(): String = "Vehicle brand: $brand"
}

class Car(brand: String, val model: String) : Vehicle(brand) {
    override fun description(): String = "Car: $brand $model"
}

fun task2E() {
    val v = Vehicle("Generic")
    val c = Car("Toyota", "Corolla")

    Log.d("Task E",v.description())
    Log.d("Task E",c.description())
}






