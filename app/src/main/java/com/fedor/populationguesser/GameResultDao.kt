package com.fedor.populationguesser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// Database access for GameResult records.
@Dao
interface GameResultDao {

    @Insert
    suspend fun insert(result: GameResult)

    @Query("SELECT * FROM game_results ORDER BY timestamp DESC")
    suspend fun getAll(): List<GameResult>
}