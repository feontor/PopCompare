package com.fedor.populationguesser

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// A single Room database for entire app. Use getDatabase rather than constructing directly.
@Database(entities = [GameResult::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gameResultDao(): GameResultDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "popcompare_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}