package com.mobile.capstonedesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_find_password_input.*
import kotlinx.android.synthetic.main.activity_sign_up_password.*
import org.jetbrains.anko.startActivity

class FindPasswordInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_password_input)

        find_password_finished_button.setOnClickListener{
            startActivity<LoginActivity>()
        }
    }
}
