package com.mobile.capstonedesign.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.jwt.JWT
import com.mobile.capstonedesign.MainActivity
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.config.JwtConfig
import com.mobile.capstonedesign.dto.request.LoginRequestDTO
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    var mBackWait: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        if (intent.getStringExtra("email") != null && intent.getStringExtra("password") != null) {
            etEmail.setText(intent.getStringExtra("email"))
            etPassword.setText(intent.getStringExtra("password"))
        }

        login_button.setOnClickListener {
            login()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }

        tvFindPassword.setOnClickListener {
            Toast.makeText(
                this,
                "${etPassword.text.toString().toInt()} \n로그인 성공",
                Toast.LENGTH_SHORT
            ).show()
        }

        tvSignUp.setOnClickListener {
            startActivity<SignUpEmailActivity>()
        }
        tvFindPassword.setOnClickListener {
            startActivity<FindPasswordCertificationActivity>()
        }

        button3.setOnClickListener {
            Toast.makeText(this, "로그인 없이 접속", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        // 뒤로가기 버튼 클릭
        if (System.currentTimeMillis() - mBackWait >= 2000) {
            mBackWait = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
//            Snackbar.make(YOURVIEW,"뒤로가기 버튼을 한번 더 누르면 종료됩니다.",Snackbar.LENGTH_LONG).show()
        } else {
            finish()                            //액티비티 종료
            moveTaskToBack(true);		// 태스크를 백그라운드로 이동
            finishAndRemoveTask()				// 액티비티 종료 + 태스크 리스트에서 지우기
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    private fun login() {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL)
            .login(LoginRequestDTO(etEmail.text.toString(), etPassword.text.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                if (items.isSuccessful) {
                    Toast.makeText(
                        this,
                        "token : \n" + items.body()?.data.toString() + "\n" + "body : \n" + items.body() + "\n로그인 성공하였습니다." + items.isSuccessful,
                        Toast.LENGTH_SHORT
                    ).show()
                    JwtConfig.TOKEN = items.body()?.data.toString()
                    var tmpToken :String = JwtConfig.TOKEN.replace("Bearer ","")
                    var jwt = JWT(tmpToken) // 토큰가져오기
                    JwtConfig.USER_ID = jwt.getClaim("id").asInt()!! // 토큰 읽기 : id 가져오기
//                    Toast.makeText(this, ""+JwtConfig.USER_ID,Toast.LENGTH_LONG).show()
                    intentMain()
                } else {
                    Toast.makeText(this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            }, {
                Toast.makeText(this, "로그인 실패\n" + it.message + "\n" + it.cause, Toast.LENGTH_SHORT)
                    .show()
            })
    }

    private fun intentMain() {
//        Toast.makeText(this,"로그인 성공", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
