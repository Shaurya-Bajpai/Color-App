package com.example.color.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColor(color: ColorEntity)

    @Query("SELECT * FROM color_table ORDER BY timestamp DESC")
    fun getAllColors(): Flow<List<ColorEntity>>

    @Query("SELECT * FROM color_table WHERE isSynced = 0")
    suspend fun getUnsyncedColors(): List<ColorEntity>

    @Query("UPDATE color_table SET isSynced = 1 WHERE id = :id")
    suspend fun markAsSynced(id: Int)
}
