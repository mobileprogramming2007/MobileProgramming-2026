package com.example.mobileprogramminglabs.model.repository

import com.example.mobileprogramminglabs.presentation.ui.screens.profile.util.ProfileData
import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FakeProfileRepository {

    suspend fun getProfileStats(): List<InfoRowData> =
        withContext(Dispatchers.IO) {
            delay(1000)

            listOf(
                InfoRowData("Completed Quests", additionalInfo = "12"),
                InfoRowData("Current Streak", additionalInfo = "5 days"),
                InfoRowData("Total XP", additionalInfo = "120")
            )
        }

    suspend fun getAdditionalRows(): List<InfoRowData> =
        withContext(Dispatchers.IO) {
            delay(1000)

            listOf(
                InfoRowData("Favorite Habit", additionalInfo = "Drink Water"),
                InfoRowData("Joined", additionalInfo = "March 2026"),
                InfoRowData("Badges", additionalInfo = "4")
            )
        }


    suspend fun getProfile(shouldFail: Boolean = false): ProfileData = withContext(Dispatchers.IO) {
        delay(2000)

        if (shouldFail) {
            throw IllegalStateException("Failed to load profile.")
        }

        ProfileData(
            name = "Ajla Korman",
            levelNo = "3",
            levelDescription = "Adventurer",
            profileStats = listOf(
                InfoRowData("Completed Quests", additionalInfo = "12"),
                InfoRowData("Current Streak", additionalInfo = "5 days"),
                InfoRowData("Total XP", additionalInfo = "120")
            ),
            additionalRows = listOf(
                InfoRowData("Favorite Habit", additionalInfo = "Drink Water"),
                InfoRowData("Joined", additionalInfo = "March 2026"),
                InfoRowData("Badges", additionalInfo = "4")
            )
        )
    }

}
