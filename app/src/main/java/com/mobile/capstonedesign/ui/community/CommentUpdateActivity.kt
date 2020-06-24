package com.mobile.capstonedesign.ui.community

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.dto.request.UpdateCommentRequestDTO
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_comment_update.*

class CommentUpdateActivity : AppCompatActivity() {

    private var comment_no: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_update)

        comment_no = intent.getIntExtra("comment_no", 0)
        etCommentUpdate.setText(intent.getStringExtra("content"))

        btnCommentUpdateSubmit.setOnClickListener {
            updateComment(comment_no, UpdateCommentRequestDTO(etCommentUpdate.text.toString()))
        }
    }

    private fun intentParent() {
        var intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun updateComment(comment_no: Int, updateCommentRequestDTO: UpdateCommentRequestDTO) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable =
            HttpClient().getApi(BASE_URL).updateComment(comment_no, updateCommentRequestDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    intentParent()
                }, { error ->
                    Toast.makeText(
                        this,
                        "덧글 수정에 실패하였습니다. " + error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                })
    }
}
