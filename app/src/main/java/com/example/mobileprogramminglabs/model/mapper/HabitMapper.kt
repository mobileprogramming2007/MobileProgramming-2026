package com.example.mobileprogramminglabs.model.mapper

import com.example.mobileprogramminglabs.model.datasource.network.dto.HabitDto
import com.example.mobileprogramminglabs.domain.data.HabitModel

fun HabitDto.toHabitModel(): HabitModel {
    return HabitModel(
        id = id,
        title = title,
        streak = frequency
    )
}