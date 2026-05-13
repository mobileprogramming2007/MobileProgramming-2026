package com.example.mobileprogramminglabs.model.datasource.network.api.mapper

import com.example.mobileprogramminglabs.model.datasource.network.api.dto.HabitDto
import com.example.mobileprogramminglabs.presentation.ui.screens.habit.util.HabitModel

fun HabitDto.toHabitModel(): HabitModel {
    return HabitModel(
        id = id,
        title = title,
        streak = frequency
    )
}