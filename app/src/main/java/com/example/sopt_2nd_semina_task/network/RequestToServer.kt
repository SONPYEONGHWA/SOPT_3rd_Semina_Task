package com.example.sopt_2nd_semina_task.network

import android.app.DownloadManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RequestToServer {
    private const val BASE_URL = "http://13.209.144.115:3333"
    private val interceptor = object : Interceptor{
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val newRequest =
                chain.request().newBuilder().addHeader("Content-Type", "application/json")
                    .build()

            return chain.proceed(newRequest)
        }
    }

    private val client = OkHttpClient.Builder().apply {
        interceptors().add(interceptor)
    }.build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    val service = retrofit.create(RequestInterface::class.java)
}
