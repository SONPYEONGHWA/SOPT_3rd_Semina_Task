package com.example.sopt_2nd_semina_task.data

data class ResponseLogin(
  val status : Int,
  val success : Boolean,
  val message : String,
  val data : Somedata?
)
class Somedata(
    val jwt : String
)