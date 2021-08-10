package com.example.mvvmexample.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmexample.data.models.db.Trash

@Dao
interface TrashDao {
    @Query("SELECT * FROM trash")
    fun getTrashes(): LiveData<List<Trash>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTrash(vararg trash: Trash)

    @Query("DELETE FROM trash")
    fun trimTrashes()
}