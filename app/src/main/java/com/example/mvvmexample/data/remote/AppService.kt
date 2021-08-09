package com.example.mvvmexample.data.remote

import com.example.mvvmexample.data.remote.api.BASE_URL
import com.example.mvvmexample.data.remote.api.TrashService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface AppService: TrashService{

    companion object {
        private var appService: AppService? = null
        private const val API_KEY = "595c184a762599e865bdb6febf4e292c"

        fun getInstance() : AppService {
            if (appService == null) {

                val builder = OkHttpClient.Builder()
                val headerInterceptor = Interceptor { chain: Interceptor.Chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("BZ-API-KEY", API_KEY)
                        .build()
                    val res = chain.proceed(newRequest)
                    res
                }

                builder.addInterceptor(headerInterceptor)
                val client = builder.build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                appService = retrofit.create(AppService::class.java)
            }
            return appService!!
        }

    }
}