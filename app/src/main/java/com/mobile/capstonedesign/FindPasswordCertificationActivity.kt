package com.mobile.capstonedesign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_find_password_certification.*




class FindPasswordCertificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_password_certification)

        FindPasswordCertification.setOnClickListener {
            val intent = Intent(this,FindPasswordInputActivity::class.java)
            startActivity(intent)
        }
    }
}