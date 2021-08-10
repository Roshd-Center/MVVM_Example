package com.example.mvvmexample.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        const val BASE_URL = "https://api.bazmand.com/"

        private const val API_KEY_NAME = "BZ-API-KEY"
        private const val API_KEY = "595c184a762599e865bdb6febf4e292c"

        var retrofit: Retrofit

        init {
            val builder = OkHttpClient.Builder()
            val headerInterceptor = Interceptor { chain: Interceptor.Chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(API_KEY_NAME, API_KEY)
                    .build()
                val res = chain.proceed(newRequest)
                res
            }

            builder.addInterceptor(headerInterceptor)
            val client = builder.build()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}