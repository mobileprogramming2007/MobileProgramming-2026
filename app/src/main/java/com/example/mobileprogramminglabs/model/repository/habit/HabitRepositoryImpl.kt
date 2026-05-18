package com.example.mobileprogramminglabs.model.repository.habit

import com.example.mobileprogramminglabs.domain.repository.HabitRepository
import com.example.mobileprogramminglabs.model.datasource.network.dto.CreateHabitDto
import com.example.mobileprogramminglabs.model.datasource.network.dto.HabitDto
import com.example.mobileprogramminglabs.model.datasource.network.dto.UpdateHabitDto
import com.example.mobileprogramminglabs.model.datasource.network.service.HabitApiService
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val api: HabitApiService
) : HabitRepository {

    override suspend fun getHabits(): List<HabitDto> = api.getHabits()

    override suspend fun getHabitById(id: Int): HabitDto = api.getHabitById(id)

    override suspend fun createHabit(habit: CreateHabitDto): HabitDto =
        api.createHabit(habit)

    override suspend fun updateHabit(id: Int, habit: UpdateHabitDto): HabitDto =
        api.updateHabit(id, habit)

    override suspend fun deleteHabit(id: Int) =
        api.deleteHabit(id)
}