package com.example.mobileprogramminglabs.model.repository.quest

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.example.mobileprogramminglabs.domain.data.QuestData
import com.example.mobileprogramminglabs.domain.repository.QuestRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class QuestRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    @ApplicationContext private val context: Context
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

    override suspend fun exportQuests(quests: List<QuestData>): Result<String> =
        withContext(Dispatchers.IO) {
            runCatching {
                val csv = buildString {
                    appendLine("id,title,xp,isCompleted")

                    quests.forEach { quest ->
                        appendLine(
                            "${quest.id},${quest.title},${quest.xp},${quest.isCompleted}"
                        )
                    }
                }

                val fileName = "quests_export.csv"

                val values = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "text/csv")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                }

                val uri = context.contentResolver.insert(
                    MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                    values
                ) ?: throw IOException("Could not create file in Downloads folder")

                context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                    outputStream.write(csv.toByteArray())
                } ?: throw IOException("Could not open output stream")

                uri.toString()
            }
        }

}
