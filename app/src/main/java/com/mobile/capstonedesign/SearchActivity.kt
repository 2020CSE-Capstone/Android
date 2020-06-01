package com.mobile.capstonedesign

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class SearchActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.hide()

        val iv_click_me = findViewById(R.id.search_arrow) as ImageView

        iv_click_me.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
          finish()
        }
    }

}

