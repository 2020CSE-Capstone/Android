package com.mobile.capstonedesign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up_email.*

class SignUpEmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_email)

        button4.setOnClickListener {
            val intent = Intent(this, SignUpCertificationNumberActivity::class.java)
            startActivity(intent)
        }
    }
}
