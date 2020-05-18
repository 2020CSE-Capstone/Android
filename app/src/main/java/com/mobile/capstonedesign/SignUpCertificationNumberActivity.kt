package com.mobile.capstonedesign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up_certification_number.*

class SignUpCertificationNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_certification_number)
        button2.setOnClickListener {
            val intent = Intent(this,SignUpPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}