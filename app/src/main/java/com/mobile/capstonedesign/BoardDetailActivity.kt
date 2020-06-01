package com.mobile.capstonedesign

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.capstonedesign.adapter.CommentRVAdapter
import com.mobile.capstonedesign.adapter.click.CommentClick
import com.mobile.capstonedesign.adapter.click.CommentMenuClick
import com.mobile.capstonedesign.dto.request.InsertCommentRequestDTO
import com.mobile.capstonedesign.dto.response.WritingDetailResponseDTO
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_board_detail.*
import java.text.SimpleDateFormat

class BoardDetailActivity : AppCompatActivity() {

    private val UPDATE_REQUEST_CODE = 200
    private val UPDATE_COMMENT_REQUEST_CODE = 200

    private val commentRVAdapter = CommentRVAdapter(this)
    private var board_no: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        board_no = intent.getIntExtra("board_no", 0)
//        Toast.makeText(this, "확인 ${intent.getIntExtra("board_no", 0)}", Toast.LENGTH_SHORT).show()

        rvComment.layoutManager = LinearLayoutManager(this)
        rvComment.adapter = commentRVAdapter
        commentRVAdapter.commentClick = object : CommentClick {
            override fun onClick(view: View, position: Int, board_no: Int, comment_no: Int) {
                intentCommentReply(board_no, comment_no)
            }
        }

        commentRVAdapter.commentMenuClick = object : CommentMenuClick {
            override fun onClick(view: View, position: Int, board_no: Int, parent_comment_no: Int, user_id: Int, content:String, comment_no: Int) {
                showPopUp(view, board_no, parent_comment_no, user_id, content, comment_no)
            }
        }

        getWritingDetailByNo(board_no)
        getAllComments(board_no, false)

        btnDetailCommentSubmit.setOnClickListener {
            if (etDetailComment.text != null) {
                insertComment(
                    InsertCommentRequestDTO(
                        etDetailComment.text.toString(), 1, board_no
                    ), true
                )
                etDetailComment.setText("")
            } else
                Toast.makeText(this, "댓글을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }

        srlDetail.setOnRefreshListener {
            getWritingDetailByNo(board_no)
            getAllComments(board_no, false)
        }
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
                UPDATE_REQUEST_CODE,
                UPDATE_COMMENT_REQUEST_CODE ->{
                    getWritingDetailByNo(board_no)
                    getAllComments(board_no, false)
                }
            }
        }
    }

    private fun showPopUp(view: View, board_no: Int, parent_comment_no: Int, user_id: Int, content:String, comment_no: Int) {
        val popupMenu = PopupMenu(this, view)
        val inflater = popupMenu.menuInflater

        /* 아이디가 자기자신일 때 my pop up */
        if (user_id == 1)
            inflater.inflate(R.menu.comment_my_popup_menu, popupMenu.menu)
        else
            inflater.inflate(R.menu.comment_common_popup_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.comment_update -> {
                    Toast.makeText(this, "${parent_comment_no}", Toast.LENGTH_SHORT).show()
                    intentCommentUpdate(comment_no,content)
                }
                R.id.comment_delete -> {
                    Toast.makeText(this, "${parent_comment_no}", Toast.LENGTH_SHORT).show()
                }
                R.id.comment_reply -> {
                    intentCommentReply(board_no, parent_comment_no)
                }
            }
            true
        }
    }

    private fun intentUpdate() {
        val intent = Intent(this, CommunityUpdateWritingActivity::class.java)
        intent
            .putExtra("board_no", board_no)
            .putExtra("title", tvDetailTitle.text)
            .putExtra("content", tvDetailContent.text)
        startActivityForResult(intent, UPDATE_REQUEST_CODE);
    }

    private fun intentCommentUpdate(comment_no: Int, content:String) {
        val intent = Intent(this, CommentUpdateActivity::class.java)
        intent.putExtra("comment_no", comment_no)
        intent.putExtra("content", content)
        startActivityForResult(intent, UPDATE_COMMENT_REQUEST_CODE);
    }

    private fun intentCommentReply(board_no: Int, comment_no: Int) {
        val intent = Intent(this, ReplyDetailActivity::class.java)
        intent.putExtra("board_no", board_no).putExtra("comment_no", comment_no)
        startActivity(intent)
    }

    private fun adReallyDelete() {
        AlertDialog.Builder(this)
            .setMessage("정말로 삭제하시겠습니까?")
            .setPositiveButton("확인") { _: DialogInterface, _: Int ->
                deleteWriting(board_no)
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

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(etDetailComment.windowToken, 0);
    }

    private fun disableSwipeRefreshProgress() {
        srlDetail.isRefreshing = false
    }

    private fun setDetailView(detailInfo: WritingDetailResponseDTO) {
        tvDetailTitle.text = detailInfo.title
        tvDetailName.text = detailInfo.nickname
        tvDetailDate.text = detailInfo.write_date
//        tvDetailDate.text = SimpleDateFormat("yyyy.MM.dd. HH:mm").format(detailInfo.write_date)
        tvDetailContent.text = detailInfo.content
        tvDetailLikeCount.text = detailInfo.like_count.toString()
        tvDetailCommentCount.text = detailInfo.comment_count.toString()
    }

    private fun getWritingDetailByNo(board_no: Int) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable =
            HttpClient().getApi(BASE_URL).getWritingDetailByNo(board_no)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    disableSwipeRefreshProgress()
                    clView.visibility = View.VISIBLE
                    setDetailView(items.data)
                }, {
                    disableSwipeRefreshProgress()
                    Toast.makeText(this, "왜안되지? \n"+it.message+"\n"+it.stackTrace.toString(), Toast.LENGTH_SHORT).show()
                    adDeletedDetail()
                })
    }

    private fun getAllComments(board_no: Int, goToBottom: Boolean) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable =
            HttpClient().getApi(BASE_URL).getAllComments(board_no)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    disableSwipeRefreshProgress()
                    commentRVAdapter.update(items.data)
                    if (goToBottom) {
                        nsvDetail.fullScroll(NestedScrollView.FOCUS_DOWN) // 이거 왜안되냐
                        hideKeyboard()
                    }
                }, {
                    disableSwipeRefreshProgress()
                    Toast.makeText(this, "댓글 불러오기 실패", Toast.LENGTH_SHORT).show()
                })
    }

    private fun insertComment(
        insertCommentRequestDTO: InsertCommentRequestDTO,
        goToBottom: Boolean
    ) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).insertComment(insertCommentRequestDTO)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Toast.makeText(this, "댓글을 등록하였습니다.", Toast.LENGTH_SHORT).show()
                getWritingDetailByNo(board_no)
                getAllComments(board_no, goToBottom)
            }, { error ->
                Toast.makeText(this, "댓글 추가에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            })
    }

    private fun deleteWriting(board_no: Int) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable =
            HttpClient().getApi(BASE_URL).deleteWriting(board_no)
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
