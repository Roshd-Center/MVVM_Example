package com.example.mvvmexample.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mvvmexample.data.local.db.dao.TrashDao
import com.example.mvvmexample.data.models.db.Trash
import com.example.mvvmexample.data.remote.api.TrashService
import retrofit2.Response

class TrashRepository constructor(private val service: TrashService, private val dao: TrashDao) {

    val trashes = dao.getTrashes()

    suspend fun getTrashes() : Response<List<Trash>> = service.getTrashes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateDbTrash(trashes: List<Trash>){
        dao.trimTrashes()
        dao.addTrash(*trashes.toTypedArray())
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun trimDbTrashes(){
        dao.trimTrashes()
    }

}