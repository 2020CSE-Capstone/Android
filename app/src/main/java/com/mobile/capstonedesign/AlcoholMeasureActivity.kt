package com.mobile.capstonedesign

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mobile.capstonedesign.dto.request.InsertDrinkRecordRequestDTO
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_alcohol_measure.*
import java.util.*


class AlcoholMeasureActivity : AppCompatActivity() {

    private var figure: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alcohol_measure)

        etSelectDate.setOnClickListener {
            calendar()
        }

        etSelectDrinkKind.setOnClickListener {
            adSelectDrinkSort()
        }

        btnMeasureSubmit.setOnClickListener {
            val date = etSelectDate.text.toString()
            val kind = etSelectDrinkKind.text.toString()
            val glass = etInputGlassCount.text.toString()

            if (date.isEmpty() || kind.isEmpty() || glass.isEmpty())
                Toast.makeText(this, "모든 항목을 기입해주세요.", Toast.LENGTH_SHORT).show()
            else {
                adReallyInsert(figure, date, kind, glass.toInt())
            }

//            Toast.makeText(this, "버튼 클릭", Toast.LENGTH_SHORT).show()
        }
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

    private fun adReallyInsert(figure: Double, date: String, kind: String, glass: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("등록하시겠습니까?")
        if (tvAmount.text == "0")
            builder.setMessage("측정을 하지 않으셨습니다.\n측정을 제외한 값을 저장하시겠습니까?")
        builder.setNegativeButton("취소", null)

        val listener = object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) { // p1이 들어오는 인덱스
                insertDrinkRecord(InsertDrinkRecordRequestDTO(figure, glass, date, kind, 1))
            }
        }

        builder.setPositiveButton("확인", listener)
        builder.show()
    }

    private fun insertDrinkRecord(record: InsertDrinkRecordRequestDTO) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).insertDrinkRecord(record)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Toast.makeText(this, result.status.toString() + " : " + result.message, Toast.LENGTH_SHORT).show()
            }, { error ->
                Toast.makeText(this, "기록 삽입 실패 " + error.message + "\n" + error.cause, Toast.LENGTH_SHORT).show()
            })
    }

}
