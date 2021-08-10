package com.example.mvvmexample.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmexample.data.local.db.dao.TrashDao
import com.example.mvvmexample.data.models.db.Trash

@Database(entities = [Trash::class,], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trashDao(): TrashDao

    companion object {
        private var database: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return database ?: synchronized(this) {
                val database = Room
                    .databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "App-Database"
                    )
                    .build()
                database
            }

        }

    }
}
