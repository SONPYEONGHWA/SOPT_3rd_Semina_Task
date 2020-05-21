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
    val requestToServer = RequestToServer
    val REQUEST_CODE = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta__login)

        init()

    }

    fun init() {
        var pref : SharedPreferences = getSharedPreferences("pref",Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = pref.edit()

        et_id_login.setText(intent.getStringExtra("id")?.toString())
        et_password_login.setText(intent.getStringExtra("pw")?.toString())
        setResult(Activity.RESULT_OK, intent)

        autoLogin()

        btn_login.setOnClickListener() {

            if (et_id_login.text.isNullOrBlank() || et_password_login.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                requestToServer.service.requestLogin(
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
                                editor.putString("id",et_id_login.text.toString())
                                editor.putString("pw",et_password_login.text.toString())
                                editor.commit()

                                val intent = Intent(this@Insta_Login_Activity, MainActivity::class.java)
                                startActivityForResult(intent,REQUEST_CODE)
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

    fun autoLogin()

    {

        var pref : SharedPreferences = getSharedPreferences("pref",Context.MODE_PRIVATE)

        if(!(pref.getString("id",null).isNullOrBlank() || pref.getString("pw",null).isNullOrBlank()))

        {
            val id = pref.getString("id",null).toString()
            if(!id.isNullOrBlank()){
                Log.d("자동로그인 id ", "${id}")
                Toast.makeText(this, "${id}님 자동로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                val intent = Intent(this, MainActivity::class.java)
                startActivityForResult(intent,REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                Log.d("로그인", "종료")
                finish()
            }
        }

    }
}