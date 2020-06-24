package com.mobile.capstonedesign.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.dto.request.SignUpRequestDTO
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_up_determination.*
import kotlinx.android.synthetic.main.activity_sign_up_determination.button4

class SignUpDeterminationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_determination)

        supportActionBar?.title = "회원가입"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val email: String? = intent.getStringExtra("email")
        val nickname: String? = intent.getStringExtra("nickname")
        val password: String? = intent.getStringExtra("password")
        val drink: Int? = intent.getIntExtra("drink", 0)
        val smoke: Int? = intent.getIntExtra("smoke", 0)

        Toast.makeText(
            this,
            email + "\n" + nickname + "\n" + password + "\n" + drink + "\n" + smoke + "\n" + etSignUpDetermination.text,
            Toast.LENGTH_SHORT
        ).show()

        button4.setOnClickListener {
            var signUp = SignUpRequestDTO(email, nickname, password, drink, smoke, etSignUpDetermination.text.toString())

            signUpRequest(signUp)
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

    private fun signUpRequest(signUp: SignUpRequestDTO) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).signUpRequest(signUp)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Toast.makeText(
                    this,
                    "가입에 성공하였습니다.\n" + result.status.toString() + " : " + result.message,
                    Toast.LENGTH_SHORT
                ).show()
                intentLogin(signUp.email, signUp.password)
            }, { error ->
                Toast.makeText(
                    this,
                    "가입에 실패하였습니다.\n" + error.message + "\n" + error.cause,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

    private fun intentLogin(email: String?, password: String?) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("password", password)
        startActivity(intent)
    }
}