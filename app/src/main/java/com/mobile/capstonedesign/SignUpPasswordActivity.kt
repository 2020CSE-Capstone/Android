package com.mobile.capstonedesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up_password.*
import org.jetbrains.anko.startActivity

class SignUpPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_password)
        button5.setOnClickListener{
            startActivity<LoginActivity>()
        }
    }
}
