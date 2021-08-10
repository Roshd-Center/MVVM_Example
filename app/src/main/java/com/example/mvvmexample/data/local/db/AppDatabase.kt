package com.example.mvvmexample.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmexample.data.local.db.dao.TrashDao
import com.example.mvvmexample.data.models.db.Trash

@Database(entities = [Trash::class,], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trashDao(): TrashDao
}
