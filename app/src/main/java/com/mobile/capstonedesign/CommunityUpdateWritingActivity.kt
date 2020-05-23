package com.mobile.capstonedesign

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobile.capstonedesign.dto.request.UpdateWritingRequestDTO
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_community_writing.*

class CommunityUpdateWritingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_writing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_red)

        if (intent.getStringExtra("title") != null || intent.getStringExtra("content") != null) {
            etTitle.setText(intent.getStringExtra("title"))
            etContent.setText(intent.getStringExtra("content"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.community_writing_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.storage -> {
                updateWriting(
                    this.intent.getIntExtra("board_no", 0),
                    UpdateWritingRequestDTO(etTitle.text.toString(), etContent.text.toString())
                )
            }
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun intentParent() {
        var intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun updateWriting(board_no: Int, updateWritingRequestDTO: UpdateWritingRequestDTO) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable1 =
            HttpClient().getApi(BASE_URL).updateWriting(board_no, updateWritingRequestDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    intentParent()
                }, { error ->
                    Toast.makeText(
                        this@CommunityUpdateWritingActivity,
                        "게시글 추가에 실패하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                })
    }
}