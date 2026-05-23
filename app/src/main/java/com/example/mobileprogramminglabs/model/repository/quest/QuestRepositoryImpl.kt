package com.example.mobileprogramminglabs.model.repository.quest

import android.util.Log
import com.example.mobileprogramminglabs.domain.data.QuestData
import com.example.mobileprogramminglabs.domain.repository.QuestRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class QuestRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : QuestRepository {

    private val questsCollection = firestore.collection("quests")

    override suspend fun getQuests(): List<QuestData> {
        return questsCollection
            .get()
            .await()
            .toObjects(QuestData::class.java)
    }

    override suspend fun addQuest(
        title: String,
        xp: Int,
        category: String,
        difficulty: String,
        isDaily: Boolean
    ) {
        val document = questsCollection.document()

        val quest = QuestData(
            id = document.id,
            userId = "",
            title = title,
            xp = xp,
            isCompleted = false,
            category = category,
            difficulty = difficulty,
            isDaily = isDaily
        )
        Log.d("FIREBASE_TEST", "Saving quest: $quest")
        document.set(quest).await()
        Log.d("FIREBASE_TEST", "Quest saved successfully with id: ${document.id}")
    }

    override suspend fun toggleQuest(questId: String, isCompleted: Boolean) {
        questsCollection
            .document(questId)
            .update("isCompleted", isCompleted)
            .await()
    }

    override suspend fun deleteQuest(questId: String) {
        questsCollection
            .document(questId)
            .delete()
            .await()
    }
}
