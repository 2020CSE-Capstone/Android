package com.mobile.capstonedesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mobile.capstonedesign.config.JwtConfig
import com.mobile.capstonedesign.dto.request.LoginRequestDTO
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_recent_writing.*
import okhttp3.ResponseBody
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        login_button.setOnClickListener {
            login()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }

        tvFindPassword.setOnClickListener {
            Toast.makeText(this,"${etPassword.text.toString().toInt()} \n로그인 성공", Toast.LENGTH_SHORT).show()
        }

        tvSignUp.setOnClickListener {
            startActivity<SignUpEmailActivity>()
        }
        tvFindPassword.setOnClickListener {
            startActivity<FindPasswordCertificationActivity>()
        }

        button3.setOnClickListener {
            Toast.makeText(this,"로그인 없이 접속", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).login(LoginRequestDTO(etTitle.text.toString(), etPassword.text.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                if(items.isSuccessful) {
                    Toast.makeText(
                        this,
                        "token : \n"+items.body()?.data.toString() + "\n" +"body : \n" + items.body() + "\n로그인 성공하였습니다."+items.isSuccessful,
                        Toast.LENGTH_SHORT
                    ).show()
                    JwtConfig.TOKEN = items.body()?.data.toString()
                    intentMain()
                } else {
                    Toast.makeText(this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            }, {
                Toast.makeText(this, "로그인 실패\n"+it.message+"\n"+it.cause, Toast.LENGTH_SHORT).show()
            })
    }

    private fun intentMain(){
//        Toast.makeText(this,"로그인 성공", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
