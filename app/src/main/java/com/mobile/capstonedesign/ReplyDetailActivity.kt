package com.mobile.capstonedesign

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.capstonedesign.adapter.CommentRVAdapter
import com.mobile.capstonedesign.adapter.CommentReplyRVAdapter
import com.mobile.capstonedesign.adapter.click.CommentReplyClick
import com.mobile.capstonedesign.dto.request.InsertCommentReplyRequestDTO
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_board_detail.*
import kotlinx.android.synthetic.main.activity_reply_detail.*

class ReplyDetailActivity : AppCompatActivity() {

    private val replyRVAdapter = CommentReplyRVAdapter(this)
    private var board_no: Int = 0
    private var comment_no: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reply_detail)

        board_no = intent.getIntExtra("board_no", 0)
        comment_no = intent.getIntExtra("comment_no", 0)

        rvReply.layoutManager = LinearLayoutManager(this)
        rvReply.adapter = replyRVAdapter

        replyRVAdapter.commentReplyClick = object : CommentReplyClick {
            override fun onClick(view: View, position: Int, nickname: String) {
                etCommentReply.setText(nickname + " ")
            }
        }

        getAllReplyComments(board_no, comment_no)

        btnDetailReplySubmit.setOnClickListener {
            if (etCommentReply.text != null) {
                insertCommentReply(
                    InsertCommentReplyRequestDTO(
                        etCommentReply.text.toString(), comment_no, 1, board_no
                    )
                )
                etCommentReply.setText("")
            } else
                Toast.makeText(this, "답글을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showPopUp(view: View, board_no: Int, comment_no: Int, user_id: Int) {
        val popupMenu = PopupMenu(this, view)
        val inflater = popupMenu.menuInflater

        /* 아이디가 자기자신일 때 my pop up */
        inflater.inflate(R.menu.comment_common_popup_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.comment_reply -> {
                    Toast.makeText(this, "${comment_no}", Toast.LENGTH_SHORT).show();
                }
            }
            true
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(etDetailComment.windowToken, 0);
    }

    private fun getAllReplyComments(board_no: Int, comment_no: Int) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable =
            HttpClient().getApi(BASE_URL).getAllReplyComments(board_no, comment_no)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    replyRVAdapter.update(items)
                    Toast.makeText(this, "대댓글 불러오기 성공", Toast.LENGTH_SHORT).show()
                }, {
//                    Toast.makeText(this, it.message+"댓글 불러오기 실패ㅡㅡㅡ", Toast.LENGTH_SHORT).show()
                })
    }

    private fun insertCommentReply(insertCommentReplyRequestDTO: InsertCommentReplyRequestDTO) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable =
            HttpClient().getApi(BASE_URL).insertCommentReply(insertCommentReplyRequestDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Toast.makeText(this, "댓글을 등록하였습니다.", Toast.LENGTH_SHORT).show()
                    getAllReplyComments(board_no, comment_no)
                    hideKeyboard()
                }, { error ->
//                    Toast.makeText(this, "댓글 추가에 실패하였습니다."+error.message, Toast.LENGTH_SHORT).show()
                })
    }

}
