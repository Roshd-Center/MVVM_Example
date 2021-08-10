package com.example.mvvmexample

import android.app.Application
import androidx.room.Room
import com.example.mvvmexample.data.local.db.AppDatabase
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    companion object {
        const val BASE_URL = "https://api.bazmand.com/"

        private const val API_KEY_NAME = "BZ-API-KEY"
        private const val API_KEY = "595c184a762599e865bdb6febf4e292c"

        lateinit var retrofit: Retrofit
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room
            .databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "App-Database"
            )
            .build()

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