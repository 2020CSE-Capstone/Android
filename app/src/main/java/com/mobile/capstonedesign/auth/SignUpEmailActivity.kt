package com.mobile.capstonedesign.auth

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_up_email.*
import java.util.regex.Pattern

class SignUpEmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_email)

        supportActionBar?.title = "회원가입"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        button4.setOnClickListener {
            if (isValidEmail(etSignUpEmail.text.toString())) {
                isEmailOverlapCheck()
            } else {
                Toast.makeText(this, "이메일 형식을 맞춰주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun isEmailOverlapCheck() {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable =
            HttpClient().getApi(BASE_URL).isEmailOverlapCheck(etSignUpEmail.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    if (items.data) {
                        Toast.makeText(this, items.message, Toast.LENGTH_SHORT).show()
                        intentToNickname()
                    } else {
                        Toast.makeText(this, items.message, Toast.LENGTH_SHORT).show()
                    }
                }, {
                    Toast.makeText(
                        this,
                        "이메일 중복 체크 실패\n" + it.message + "\n" + it.cause,
                        Toast.LENGTH_SHORT
                    ).show()
                })
    }

    private fun isValidEmail(email: String): Boolean {
        return Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{3,})$", email)
    }

    private fun intentToNickname() {
        val intent = Intent(this, SignUpNicknameActivity::class.java)
        intent.putExtra("email", etSignUpEmail.text.toString())
        startActivity(intent)
    }
}
