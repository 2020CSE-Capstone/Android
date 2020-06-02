package com.mobile.capstonedesign

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_alcohol_measure.*
import java.util.*


class AlcoholMeasureActivity : AppCompatActivity() {

    var data1 = arrayOf(
        "항목1",
        "항목1",
        "항목1",
        "항목1",
        "항목1",
        "항목6",
        "항목6",
        "항목6",
        "항목6",
        "항목6",
        "항목6",
        "항목6",
        "항목6",
        "항목6"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alcohol_measure)

        etSelectDate.setOnClickListener {
            calendar()
        }

        etSelectDrinkKind.setOnClickListener {
            adSelectDrinkSort()
        }

//        val items = resources.getStringArray(R.array.korea_tobacco)
//        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
//
//        spSelectDrinkKind.adapter = myAdapter
//
//        spSelectDrinkKind.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>,
//                    view: View,
//                    position: Int,
//                    id: Long
//                ) {
//                    when (position) {
//                        0 -> {
//                            tvSelectedTobacco.text = items[0]
//                        }
//                        1 -> {
//                            tvSelectedTobacco.text = items[1]
//                        }
//                        2 -> {
//                            tvSelectedTobacco.text = items[2]
//                        }
//                        3 -> {
//                            tvSelectedTobacco.text = items[3]
//                        }
//                        else -> {
//                            tvSelectedTobacco.text = items[4]
//                        }
//                    }
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {
//
//                }
//            }
    }

    private fun calendar() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            // i년 i2월 i3일
            etSelectDate.setText("${i}년 ${i2 + 1}월 ${i3}일")
        }

        val picker = DatePickerDialog(this, listener, year, month, day)
        picker.show()
    }

    private fun adSelectDrinkSort() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("주종을 선택해주세요.")
        builder.setNegativeButton("취소", null)

        val listener = object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) { // p1이 들어오는 인덱스
                val items = resources.getStringArray(R.array.drink_sort)
                etSelectDrinkKind.setText("${items[p1]}")
            }
        }

        builder.setItems(R.array.drink_sort, listener)
        builder.show()
    }
}
