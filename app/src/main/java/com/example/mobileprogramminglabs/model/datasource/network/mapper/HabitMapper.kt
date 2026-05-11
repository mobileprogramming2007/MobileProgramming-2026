package com.example.mobileprogramminglabs.model.datasource.network.mapper

import com.example.mobileprogramminglabs.model.datasource.network.dto.HabitDto
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.util.HabitModel

fun HabitDto.toHabitModel(): HabitModel {
    return HabitModel(
        id = id,
        title = title,
        streak = frequency
    )
}