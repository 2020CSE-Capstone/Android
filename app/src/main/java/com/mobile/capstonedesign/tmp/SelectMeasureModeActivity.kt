package com.mobile.capstonedesign.tmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.TobaccoMeasureActivity
import kotlinx.android.synthetic.main.activity_select_measure_mode.*
import org.jetbrains.anko.startActivity

class SelectMeasureModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_measure_mode)
//        supportActionBar?.hide()
        supportActionBar?.title = "측정 화면"

        alcohol_measure_button.setOnClickListener {
            startActivity<MeasureActivity>()
        }
        tobacco_measure_button.setOnClickListener {
            startActivity<TobaccoMeasureActivity>()
        }
    }
}
