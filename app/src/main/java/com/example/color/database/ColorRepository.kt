package com.example.color.database

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ColorRepository(
    private val dao: ColorDao,
    private val firebaseDb: DatabaseReference = Firebase.database.reference.child("colors")
) {

    val allColors: Flow<List<ColorEntity>> = dao.getAllColors()

    suspend fun addColor(color: String, timestamp: Long) {
        val newColor = ColorEntity(color = color, timestamp = timestamp, isSynced = false)
        dao.insertColor(newColor)
    }

    suspend fun syncColors() {
        val unsyncedColors = dao.getUnsyncedColors()
        unsyncedColors.forEach { colorEntity ->
            try {
                // Push data to Firebase
                val colorData = mapOf(
                    "color" to colorEntity.color,
                    "timestamp" to colorEntity.timestamp
                )
//                val userRef = firebaseDb.child(colorEntity.timestamp.toString())
//                userRef.setValue(colorData).await()
                firebaseDb.push().setValue(colorData).await()
                dao.markAsSynced(colorEntity.id)  // Mark as synced locally
            } catch (e: Exception) {
                Log.e("ColorRepository", "Sync failed: ${e.message}")
            }
        }
    }
}
