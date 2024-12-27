package com.example.color.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "color_table")
data class ColorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val color: String,
    val timestamp: Long,
    val isSynced: Boolean = false
)
