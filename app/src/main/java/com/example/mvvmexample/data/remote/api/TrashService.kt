package com.example.mvvmexample.data.remote.api

import com.example.mvvmexample.data.models.db.Trash
import retrofit2.Response
import retrofit2.http.GET



interface TrashService {
    @GET(TRASH_GET)
    suspend fun getTrashes(): Response<List<Trash>>
}