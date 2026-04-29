package com.example.mobileprogramminglabs.model.repository

import com.example.mobileprogramminglabs.presentation.ui.screens.habit.util.HabitModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FakeHabitRepository {

    suspend fun getHabits(shouldFail: Boolean = false): List<HabitModel> =
        withContext(Dispatchers.IO) {
            delay(1500)

            if (shouldFail) {
                throw IllegalStateException("Failed to load habits.")
            }

            listOf(
                HabitModel(title = "Drink Water", streak = "5 days"),
                HabitModel(title = "Read 10 Pages", streak = "3 days"),
                HabitModel(title = "Sleep 8 Hours", streak = "7 days"),
                HabitModel(title = "Morning Walk", streak = "4 days"),
                HabitModel(title = "Meditate", streak = "6 days"),
                HabitModel(title = "Write Journal", streak = "2 days"),
                HabitModel(title = "No Sugar", streak = "8 days"),
                HabitModel(title = "Study Kotlin", streak = "10 days"),
                HabitModel(title = "Exercise", streak = "9 days"),
                HabitModel(title = "Stretching", streak = "4 days"),
                HabitModel(title = "Practice English", streak = "12 days"),
                HabitModel(title = "Eat Breakfast", streak = "11 days"),
                HabitModel(title = "Limit Screen Time", streak = "3 days"),
                HabitModel(title = "Go to Bed Early", streak = "5 days"),
                HabitModel(title = "Review Notes", streak = "7 days"),
                HabitModel(title = "Clean Room", streak = "2 days"),
                HabitModel(title = "Take Vitamins", streak = "14 days"),
                HabitModel(title = "Daily Planning", streak = "6 days"),
                HabitModel(title = "Learn Compose", streak = "8 days"),
                HabitModel(title = "Evening Walk", streak = "4 days")
            )
        }
}
