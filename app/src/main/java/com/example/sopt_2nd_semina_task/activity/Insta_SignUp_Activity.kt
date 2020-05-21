package com.example.sopt_2nd_semina_task.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sopt_2nd_semina_task.R
import com.example.sopt_2nd_semina_task.data.RequestSignup
import com.example.sopt_2nd_semina_task.data.ResponseSignup
import com.example.sopt_2nd_semina_task.network.RequestToServer
import kotlinx.android.synthetic.main.activity_insta__sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Insta_SignUp_Activity : AppCompatActivity() {

    val REQUEST_CODE_LOGIN = 100
    val requestToSever : RequestToServer = RequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta__sign_up)

        btn_signup.setOnClickListener {

            if(et_id_signup.text.isNullOrBlank() || et_password_signup.text.isNullOrBlank()){
                Toast.makeText(this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                if(et_password_signup.text.toString().equals(et_password_check.text.toString()) ||
                    et_name.text.toString().isNullOrBlank() ||
                    et_email_signup.text.isNullOrBlank() ||
                    et_phonenumber.text.toString().isNullOrBlank()){

                    requestToSever.service.requestSignup(
                        RequestSignup(
                            id = et_id_signup.text.toString(),
                            password = et_password_signup.text.toString(),
                            name = et_name.text.toString(),
                            email = et_email_signup.text.toString(),
                            phonenumber = et_phonenumber.text.toString()
                        )
                    ).enqueue(object : Callback<ResponseSignup>{
                        override fun onFailure(call: Call<ResponseSignup>, t: Throwable) {
                            Log.d("통신실패","${t}")
                        }

                        override fun onResponse(
                            call: Call<ResponseSignup>,
                            response: Response<ResponseSignup>
                        ) {
                            if(response.isSuccessful) {
                                if(response.body()!!.success){
                                    Toast.makeText(this@Insta_SignUp_Activity, "회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show()

                                    val intent = Intent(this@Insta_SignUp_Activity,Insta_Login_Activity::class.java)
                                    intent.putExtra("id", et_id_signup.text.toString())
                                    intent.putExtra("pw", et_password_signup.text.toString())
                                    startActivityForResult(intent,REQUEST_CODE_LOGIN)
                                } else {
                                    if(response.body()!!.status == 200) {
                                        Toast.makeText(applicationContext,"${response.body()!!.message}",Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }

                    })
            } else{
                Toast.makeText(this,"아이디와 비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show()
              }
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK)
        {
            if(requestCode==REQUEST_CODE_LOGIN)
            {
                finish()
            }
        }
    }
}
