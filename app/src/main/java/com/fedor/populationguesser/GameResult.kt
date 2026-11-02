package com.fedor.populationguesser

import androidx.room.Entity
import androidx.room.PrimaryKey

// Record of one completed quiz run, used to persist stats across all runs.
@Entity(tableName = "game_results")
data class GameResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val difficulty: String,
    val score: Int,
    val totalAnswered: Int,
    val timestamp: Long = System.currentTimeMillis()
)
