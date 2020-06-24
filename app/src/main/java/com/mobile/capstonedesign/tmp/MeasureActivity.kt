package com.mobile.capstonedesign.tmp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.mobile.capstonedesign.R
import kotlinx.android.synthetic.main.activity_measure.*
import java.util.*


class MeasureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure)

        supportActionBar?.title = "음주 측정"

        btnSelectDate.setOnClickListener {
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                // i년 i2월 i3일
                tvSelectedDate.text = "${i}년 ${i2 + 1}월 ${i3}일"
            }

            var picker = DatePickerDialog(this, listener, year, month, day)
            picker.show()
        }

        val items = resources.getStringArray(R.array.drink_sort)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        spSelectAlcoholicDrink.adapter = myAdapter

        spSelectAlcoholicDrink.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long ) {
                    when (position) {
                        0 -> {
                            tvSelectedAlcoholicDrink.text = items[0]
                        }
                        1 -> {
                            tvSelectedAlcoholicDrink.text = items[1]
                        }
                        2 -> {
                            tvSelectedAlcoholicDrink.text = items[2]
                        }
                        3 -> {
                            tvSelectedAlcoholicDrink.text = items[3]
                        }
                        else -> {
                            tvSelectedAlcoholicDrink.text = items[4]
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
    }
}
