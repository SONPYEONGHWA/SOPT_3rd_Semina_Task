package com.example.sopt_2nd_semina_task.activity

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sopt_2nd_semina_task.R
import com.example.sopt_2nd_semina_task.data.RequestLogin
import com.example.sopt_2nd_semina_task.data.ResponseLogin
import com.example.sopt_2nd_semina_task.network.RequestToServer
import kotlinx.android.synthetic.main.activity_insta__login.*
import kotlinx.android.synthetic.main.activity_insta__sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Insta_Login_Activity : AppCompatActivity() {
    val requestToServer: RequestToServer = RequestToServer
    val REQUEST_CODE = 300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta__login)

        init()

    }

    fun init() {

        setResult(Activity.RESULT_OK, intent)

        btn_login.setOnClickListener() {

            if (et_id_login.text.isNullOrBlank() || et_password_login.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                requestToServer.service.requstLogin(
                    RequestLogin(
                        id = et_id_login.text.toString(),
                        password = et_password_login.text.toString()
                    )
                ).enqueue(object : Callback<ResponseLogin> {
                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        Log.d("로그인 통신실패", "${t}")
                    }

                    override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body()!!.success) {
                                val intent = Intent(this@Insta_Login_Activity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@Insta_Login_Activity, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }

        tv_gotosignup.setOnClickListener() {
            val intent = Intent(this, Insta_SignUp_Activity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                et_id_login.setText(data!!.getStringExtra("id"))
                et_password_login.setText(data!!.getStringExtra("pw"))
                Log.d("로그인", "종료")
                finish()
            }

        }

    }
}