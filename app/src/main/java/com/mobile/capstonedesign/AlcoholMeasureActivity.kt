package com.mobile.capstonedesign

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_alcohol_measure.*
import kotlinx.android.synthetic.main.activity_tobacco_measure.*
import java.util.*

class AlcoholMeasureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alcohol_measure)

        etSelectDate.setOnClickListener {
            calendar()
        }

        val items = resources.getStringArray(R.array.korea_tobacco)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        spSelectDrinkKind.adapter = myAdapter

        spSelectDrinkKind.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            tvSelectedTobacco.text = items[0]
                        }
                        1 -> {
                            tvSelectedTobacco.text = items[1]
                        }
                        2 -> {
                            tvSelectedTobacco.text = items[2]
                        }
                        3 -> {
                            tvSelectedTobacco.text = items[3]
                        }
                        else -> {
                            tvSelectedTobacco.text = items[4]
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
    }

    private fun calendar(){
        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        var listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            // i년 i2월 i3일
            etSelectDate.setText("${i}년 ${i2 + 1}월 ${i3}일")
        }

        var picker = DatePickerDialog(this, listener, year, month, day)
        picker.show()
    }
}
