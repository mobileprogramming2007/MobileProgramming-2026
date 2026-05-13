package com.example.mobileprogramminglabs.model.repository.habit

import com.example.mobileprogramminglabs.model.datasource.network.api.dto.CreateHabitDto
import com.example.mobileprogramminglabs.model.datasource.network.api.dto.HabitDto
import com.example.mobileprogramminglabs.model.datasource.network.api.dto.UpdateHabitDto

interface HabitRepository {
    suspend fun getHabits(): List<HabitDto>
    suspend fun getHabitById(id: Int): HabitDto
    suspend fun createHabit(habit: CreateHabitDto): HabitDto
    suspend fun updateHabit(id: Int, habit: UpdateHabitDto): HabitDto
    suspend fun deleteHabit(id: Int)
}