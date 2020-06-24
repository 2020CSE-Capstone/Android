package com.mobile.capstonedesign.auth

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobile.capstonedesign.R
import kotlinx.android.synthetic.main.activity_sign_up_password.*

class SignUpPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_password)

        supportActionBar?.title = "회원가입"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        var email: String = intent.getStringExtra("email");
        var nickname: String = intent.getStringExtra("nickname");

        button5.setOnClickListener {
            if (etPassword.length() < 6) {
                Toast.makeText(this, "비밀번호를 6자 이상 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if ((etPassword.text.toString() != etPasswordCheck.text.toString())) {
                Toast.makeText(this, "입력하신 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SignUpAmountActivity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("nickname", nickname)
                intent.putExtra("password", etPasswordCheck.text.toString())
                startActivity(intent)
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
}