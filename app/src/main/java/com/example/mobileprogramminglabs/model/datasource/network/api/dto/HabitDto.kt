package com.example.mobileprogramminglabs.model.datasource.network.api.dto

data class HabitDto(
    val id: Int,
    val title: String,
    val description: String,
    val frequency: String,
    val completed: Boolean,
    val user_id: Int
)

data class CreateHabitDto(
    val title: String,
    val description: String,
    val frequency: String,
    val completed: Boolean = false,
    val user_id: Int
)

data class UpdateHabitDto(
    val title: String? = null,
    val description: String? = null,
    val frequency: String? = null,
    val completed: Boolean? = null,
    val user_id: Int? = null
)