package com.example.sopt_2nd_semina_task.network

import com.example.sopt_2nd_semina_task.data.RequestLogin
import com.example.sopt_2nd_semina_task.data.RequestSignup
import com.example.sopt_2nd_semina_task.data.ResponseLogin
import com.example.sopt_2nd_semina_task.data.ResponseSignup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface RequestInterface {

    @POST("/user/signin")
    fun requestLogin(@Body body: RequestLogin): Call<ResponseLogin>

    @POST("/user/signup")
    fun requestSignup(@Body body: RequestSignup): Call<ResponseSignup>

}