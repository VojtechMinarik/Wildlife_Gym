package com.example.wildlifegym.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * This class defines an AppDatabase class to hold the database.
 * AppDatabase defines the database configuration and serves as the app's main access point to the persisted data.
 */
@Database(entities = [Animal::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun databaseDao(): AnimalDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "animal_database"
                )
                    .createFromAsset("database/Animal.db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}