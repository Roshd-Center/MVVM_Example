package com.example.mvvmexample.data

import com.example.mvvmexample.data.remote.AppService

class MainRepository constructor(private val retrofitService: AppService) {

    suspend fun getTrashes() = retrofitService.getTrashes()

}