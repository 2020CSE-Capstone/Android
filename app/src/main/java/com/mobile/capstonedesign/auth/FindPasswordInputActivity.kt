package com.mobile.capstonedesign.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobile.capstonedesign.R
import kotlinx.android.synthetic.main.activity_find_password_input.*
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
