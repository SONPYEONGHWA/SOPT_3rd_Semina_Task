## 최종 결과화면!!!


<img src ="https://user-images.githubusercontent.com/56873136/82593914-37d56b80-9bde-11ea-8a05-b0815ed6fd15.jpg" width="230" height="430">                         <img src ="https://user-images.githubusercontent.com/56873136/82592885-8aae2380-9bdc-11ea-810f-282a11285e30.jpg" width="230" height="430">
<img src ="https://user-images.githubusercontent.com/56873136/82634921-fecae480-9c39-11ea-9945-47ea85785ace.jpg" width="230" height="430"> <img src ="https://user-images.githubusercontent.com/56873136/82635061-51a49c00-9c3a-11ea-9b85-5b60adc6948b.jpg" width="230" height="430"> <img src ="https://user-images.githubusercontent.com/56873136/82635210-ac3df800-9c3a-11ea-906b-c811527a0bab.jpg" width="230" height="430"> <img src ="https://user-images.githubusercontent.com/56873136/82635132-8284d100-9c3a-11ea-9c42-c4b5c039864e.jpg" width="230" height="430">

## 라이브러리 추가하기

**build.gradle**

    implementation "com.google.android.material:material:1.2.0-alpha05"  
    implementation "com.github.bumptech.glide:glide:4.10.0"  
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'  
    kapt "com.github.bumptech.glide:compiler:4.10.0"

**Manifest.xml**
   
     //인터넷을 사용을 허용해준다.
       <uses-permission android:name="android.permission.INTERNET" />

     //모든 http 사이트에 대한 접근을 허용해 주기 위해 true true로 변경해준다.
        android:usesCleartextTraffic="true"

## Request/Response 객체 설계

**로그인과 회원가입 API문서를 참고하여  Request / Response 객체를 설계해준다.**

==**RequestLogin**==

    data class RequestLogin(  
    val id : String,  
    val password : String  
    )

==**ResponseLogin**==

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

**- @SerializedName을 사용하여  서버에서 지정해준 변수가 아닌 원하는 변수명으로 변경하여 사용가능하다.**

==**RequestSignup**==

    data class RequestSignup(  
     val id : String,  
     val password : String,  
     val name : String,  
     val email : String,  
     val phone : String  
    )
    
**ResponseSignup**

    data class ResponseSignup (  
    val status : Int,  
    val success : Boolean,  
    val message : String  
    )

## Retrofit 인터페이스 만들기

==**RequestInterface**==
  
    interface RequestInterface {  
      
        @POST("/user/signin")  
        fun requestLogin(@Body body: RequestLogin): Call<ResponseLogin>  
      
        @POST("/user/signup")  
        fun requestSignup(@Body body: RequestSignup): Call<ResponseSignup> 
    }


## 인터페이스의 실제 구현체 만들기

==**RequestToServer**==

    object RequestToServer{  
      var retrofit = Retrofit.Builder()
      .baseUrl("http://13.209.144.115:3333")
      .addConverterFactory(GsonConverterFactory.create())
      .build()  
          
       var service : RequestInterfaceretrofit.create(RequestInterface::class.java)  
    }

## 서버와 통신!!


**Call<Type>** --  비동기적으로 Type을 받아오는 객체

**Callback<Type>** -- Type 객체를 받아왔을 때, 프로그래머가 할 행동

 ==**-- Insta_SignUp_Activity.kt**==

    val requestToServer = RequestToServer  
  
    if(et_name.text.isNullOrBlank() || et_phonenumber.text.isNullOrBlank() || et_email_signup.text.isNullOrBlank()) {  
    Toast.makeText(applicationContext, "필수입력사항을 확인해주세요", Toast.LENGTH_SHORT).show()  
    } else {  
    requestToServer.service.requestSignup(  
        RequestSignup(  
        id = et_id_signup.text.toString(),  
        password = et_password_signup.text.toString(),  
        name = et_name.text.toString(),  
        email = et_email_signup.text.toString(),  
        phone = et_phonenumber.text.toString()  
    )).enqueue(  
            object : Callback<ResponseSignup> {  
                override fun onFailure(call: Call<ResponseSignup>, t: Throwable) {  
                    Log.d("통신실패", "${t}")  
                }  
                override fun onResponse(  
                    call: Call<ResponseSignup>,  
     response: Response<ResponseSignup>  
                ) {  
                    if (response.isSuccessful) {  
                        if (response.body()!!.success) {  
                            Toast.makeText(this@Insta_SignUp_Activity, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()  
                            val intent = Intent(this@Insta_SignUp_Activity, Insta_Login_Activity::class.java)  
  
                            editor.putString("id", et_id_signup.text.toString())  
                            editor.putString("pw", et_password_signup.text.toString())  
                            editor.commit()  
  
                            intent.putExtra("id", et_id_signup.text.toString())  
                            intent.putExtra("pw", et_password_signup.text.toString())  
                            startActivityForResult(intent, REQUEST_CODE_LOGIN)  
                        } else {  
                            if (response.body()!!.status == 200) {  
                                Toast.makeText(this@Insta_SignUp_Activity, "${response.body()!!.message}", Toast.LENGTH_SHORT).show()  
                            }  
                        }  
                    }  
                }  
            }  
        )  
    }

 ==**-- Insta_Login_Activity.kt**==

    class Insta_Login_Activity : AppCompatActivity() {  
  
    val REQUEST_CODE_MAIN = 1000  
    val requestToServer = RequestToServer  
    var pref : SharedPreferences = getSharedPreferences("pref",Context.MODE_PRIVATE)  
    var editor : SharedPreferences.Editor = pref.edit()  
  
    override fun onCreate(savedInstanceState: Bundle?) {  
        super.onCreate(savedInstanceState)  
        setContentView(R.layout.activity_insta__login)  
  
        et_id_login.setText(intent.getStringExtra("id")?.toString())  
        et_password_login.setText(intent.getStringExtra("pw")?.toString())  
        setResult(Activity.RESULT_OK,intent)  
  
        autoLogin()  
  
        btn_login.setOnClickListener {  
  
    if(et_id_login.text.isNullOrBlank() || et_password_login.text.isNullOrBlank()) {  
                Toast.makeText(this, "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();  
    } else { requestToServer.service.requestLogin(  
             RequestLogin(
                  id = et_id_login.text.toString(),  
                  password = et_password_login.text.toString())  
             ).enqueue(object : retrofit2.Callback<ResponseLogin> {  
  
                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {  
                        Log.d("로그인 통신실패", "${t}") }  
  
                    override fun onResponse(  
                        call: Call<ResponseLogin>,  
    response: Response<ResponseLogin>  
                    ){ if(response.isSuccessful) {  
                        if(response.body()!!.success){   
                            Toast.makeText(this@Insta_Login_Activity, "로그인 성공", Toast.LENGTH_SHORT).show()  
                            editor.putString("id",et_id_login.text.toString())  
                            editor.putString("pw",et_password_login.text.toString())  
                            editor.commit()  
  
                            val intent = Intent(this@Insta_Login_Activity, MainActivity::class.java)  
                            startActivityForResult(intent,REQUEST_CODE_MAIN)  
  
                        } else {  
                                Toast.makeText(this@Insta_Login_Activity, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();  
                       }  
                     }  
                   }  
                })  
            }  
        }  
  
    tv_gotosignup.setOnClickListener {  
    val intent = Intent(this, Insta_SignUp_Activity::class.java)  
            startActivity(intent)  
        }  
    }
