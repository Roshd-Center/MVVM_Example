package com.example.mvvmexample.data.remote.api

import com.example.mvvmexample.data.models.db.Trash
import com.example.mvvmexample.data.remote.RetroInstance.Companion.retrofit
import retrofit2.Response
import retrofit2.http.GET



interface TrashService {
    @GET(TRASH_GET)
    suspend fun getTrashes(): Response<List<Trash>>


    companion object {
        private var appService: TrashService? = null

        fun getInstance() : TrashService {
            if (appService == null) {
                appService = retrofit.create(TrashService::class.java)
            }
            return appService!!
        }

    }
}