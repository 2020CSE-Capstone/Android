package com.mobile.capstonedesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        login_button.setOnClickListener {
            startActivity<MainActivity>()
        }

        tvFindPassword.setOnClickListener {
        }

        tvSignUp.setOnClickListener {
            startActivity<SignUpEmailActivity>()
        }
        tvFindPassword.setOnClickListener {
            startActivity<FindPasswordCertificationActivity>()
        }
    }
}
