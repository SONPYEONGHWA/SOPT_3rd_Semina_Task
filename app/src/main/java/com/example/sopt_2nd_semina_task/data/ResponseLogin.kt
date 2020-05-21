package com.example.sopt_2nd_semina_task.data

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
  val status : Int,
  val success : Boolean,
  val message : String,
  @SerializedName("data")
  val responseData : Somedata?
)
data class Somedata(
    val jwt : String
)