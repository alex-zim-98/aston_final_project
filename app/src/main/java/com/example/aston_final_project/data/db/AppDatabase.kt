package com.example.aston_final_project.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aston_final_project.data.db.dao.CachedSearchDao
import com.example.aston_final_project.data.db.entity.CashTable

@Database(entities = [CashTable::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null
        fun getDataBase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val room = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                instance = room
                room
            }
        }

        private const val DB_NAME = "app_database"
    }


    abstract fun cachedSearchDao(): CachedSearchDao
}