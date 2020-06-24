package com.mobile.capstonedesign.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobile.capstonedesign.R
import kotlinx.android.synthetic.main.activity_find_password_certification.*

class FindPasswordCertificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_password_certification)

        FindPasswordCertification.setOnClickListener {
            val intent = Intent(this,
                FindPasswordInputActivity::class.java)
            startActivity(intent)
        }
    }
}