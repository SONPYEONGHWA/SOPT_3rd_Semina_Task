package com.example.sopt_2nd_semina_task.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RequestToServer{
 var retrofit = Retrofit.Builder()
     .baseUrl("http://13.209.144.115:3333").addConverterFactory(GsonConverterFactory.create()).build()
    
 var service : RequestInterface = retrofit.create(RequestInterface::class.java)
}