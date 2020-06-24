package com.mobile.capstonedesign.ui.community

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.config.JwtConfig
import com.mobile.capstonedesign.dto.request.InsertWritingRequestDTO
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_community_writing.*

class CommunityWritingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_writing)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black)

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
                insertWriting(
                    InsertWritingRequestDTO(
                        etTitle.text.toString(),
                        etContent.text.toString(),
                        JwtConfig.USER_ID
                    )
                )
                finish()
            }
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun insertWriting(insertWritingRequestDTO: InsertWritingRequestDTO) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).insertWriting(insertWritingRequestDTO)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Toast.makeText(this@CommunityWritingActivity, "게시글을 등록하였습니다.", Toast.LENGTH_SHORT)
                    .show()
            }, { error ->
                Toast.makeText(
                    this@CommunityWritingActivity,
                    error.message + "\n" + error.cause +
                            "게시글 추가에 실패하였습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}