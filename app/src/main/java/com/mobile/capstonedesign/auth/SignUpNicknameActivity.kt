package com.mobile.capstonedesign.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_up_email.*
import kotlinx.android.synthetic.main.activity_sign_up_email.button4
import kotlinx.android.synthetic.main.activity_sign_up_nickname.*

class SignUpNicknameActivity : AppCompatActivity() {

    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_nickname)

        supportActionBar?.title = "회원가입"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        email = intent.getStringExtra("email")

        button4.setOnClickListener {
            isNicknameOverlapCheck()
        }
    }

    private fun isNicknameOverlapCheck() {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable =
            HttpClient().getApi(BASE_URL).isNicknameOverlapCheck(etSignUpNickname.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    if (items.data) {
                        Toast.makeText(this, items.message, Toast.LENGTH_SHORT).show()
                        intentToPassword()
                    } else {
                        Toast.makeText(this, items.message, Toast.LENGTH_SHORT).show()
                    }
                }, {
                    Toast.makeText(
                        this,
                        "닉네임 중복 체크 실패\n" + it.message + "\n" + it.cause,
                        Toast.LENGTH_SHORT
                    ).show()
                })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun intentToPassword() {
        val intent = Intent(this, SignUpPasswordActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("nickname", etSignUpNickname.text.toString())
        startActivity(intent)
    }
}