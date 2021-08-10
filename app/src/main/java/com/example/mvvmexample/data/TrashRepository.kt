package com.example.mvvmexample.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mvvmexample.data.local.db.dao.TrashDao
import com.example.mvvmexample.data.models.db.Trash
import com.example.mvvmexample.data.remote.api.TrashService

class TrashRepository constructor(private val service: TrashService, private val dao: TrashDao) {

    suspend fun getTrashes() = service.getTrashes()

}