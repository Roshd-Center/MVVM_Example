package com.example.mvvmexample.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmexample.data.models.db.Trash

@Dao
interface TrashDao {
    @Query("SELECT * FROM trash")
    fun getTrashes(): LiveData<List<Trash>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTrash(vararg trash: Trash): LiveData<List<Trash>>
}