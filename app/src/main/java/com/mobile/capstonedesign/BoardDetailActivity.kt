package com.mobile.capstonedesign

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.capstonedesign.adapter.CommentRVAdapter
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_board_detail.*
import java.text.SimpleDateFormat

class BoardDetailActivity : AppCompatActivity() {

    private val UPDATE_REQUEST_CODE = 200

    private val commentRVAdapter = CommentRVAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        rvComment.layoutManager = LinearLayoutManager(this)
        rvComment.adapter = commentRVAdapter

        getWritingDetailByNo(intent.getIntExtra("board_no", 0))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_options_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.detail_update
            -> {
                Toast.makeText(this, "게시글 수정", Toast.LENGTH_SHORT).show()
                intentUpdate()
            }
            R.id.detail_delete
            -> {
                Toast.makeText(this, "게시글 삭제", Toast.LENGTH_SHORT).show()
                adReallyDelete()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                UPDATE_REQUEST_CODE -> {
                    getWritingDetailByNo(intent.getIntExtra("board_no", 0))
                }
            }
        }
    }

    private fun intentUpdate() {
        val intent = Intent(this, CommunityUpdateWritingActivity::class.java)
        intent
            .putExtra("board_no", this.intent.getIntExtra("board_no", 0))
            .putExtra("title", tvDetailTitle.text)
            .putExtra("content", tvDetailContent.text)
        startActivityForResult(intent, UPDATE_REQUEST_CODE);
    }

    private fun adReallyDelete() {
        AlertDialog.Builder(this)
            .setMessage("정말로 삭제하시겠습니까?")
            .setPositiveButton("확인") { _: DialogInterface, _: Int ->
                deleteWriting(this.intent.getIntExtra("board_no", 0))
                finish()
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun adDeletedDetail() {
        AlertDialog.Builder(this)
            .setMessage("삭제된 게시글입니다.")
            .setPositiveButton("확인") { _: DialogInterface, _: Int ->
                finish()
            }
            .setNegativeButton("취소") { _: DialogInterface, _: Int ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun getWritingDetailByNo(board_no: Int) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable =
            HttpClient().getApi(BASE_URL).getWritingDetailByNo(board_no)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    clView.visibility = View.VISIBLE
                    tvDetailTitle.text = items.title
                    tvDetailName.text = items.user_id.toString()
                    tvDetailDate.text =
                        SimpleDateFormat("yyyy.MM.dd. HH:mm").format(items.write_date)
                    tvDetailContent.text = items.content
                    tvDetailLikeCount.text = items.like_count.toString()
                    tvDetailCommentCount.text = "아직 구현 안함"
                }, {
                    adDeletedDetail()
                })
    }

    private fun deleteWriting(board_no: Int) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable1 = HttpClient().getApi(BASE_URL).deleteWriting(board_no)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Toast.makeText(this, "게시글 삭제 성공", Toast.LENGTH_SHORT).show()
                finish()
            }, { error ->
                Toast.makeText(
                    this, "게시글 삭제 실패", Toast.LENGTH_SHORT
                ).show()
            })
    }
}
