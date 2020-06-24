package com.mobile.capstonedesign.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mobile.capstonedesign.R
import kotlinx.android.synthetic.main.activity_sign_up_amount.*

class SignUpAmountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_amount)

        supportActionBar?.title = "회원가입"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        var email : String = intent.getStringExtra("email")
        var nickname : String = intent.getStringExtra("nickname")
        var password : String = intent.getStringExtra("password")

        button5.setOnClickListener {
            val intent = Intent(this, SignUpDeterminationActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("nickname", nickname)
            intent.putExtra("password", password)
            intent.putExtra("drink", etSignUpDrinkAmount.text.toString().toInt())
            intent.putExtra("smoke", etSignUpSmokeAmount.text.toString().toInt())
            startActivity(intent)
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