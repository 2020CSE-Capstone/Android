package com.mobile.capstonedesign.ui.community

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.adapter.click.BoardClick
import com.mobile.capstonedesign.http.HttpClient
import com.mobile.capstonedesign.adapter.WritingRVAdapter
import com.mobile.capstonedesign.config.JwtConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_my_writing.*

class MyWritingFragment : Fragment() {

    private val writingRVAdapter =
        WritingRVAdapter(context)

    companion object {
        fun newInstance() = MyWritingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_writing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMyWriting.layoutManager = LinearLayoutManager(activity)
        rvMyWriting.adapter = writingRVAdapter
        writingRVAdapter.boardClick = object :
            BoardClick {
            override fun onClick(view: View, position: Int, no: Int, user_id: Int) {
                intentDetail(no, user_id)
            }
        }

        srlMyPage.setOnRefreshListener {
            getUserAllWriting()
        }

        getUserAllWriting()
    }

    private fun intentDetail(board_no: Int, user_id: Int) {
        val intent = Intent(activity, BoardDetailActivity::class.java)
        intent.putExtra("board_no", board_no)
        intent.putExtra("user_id", user_id)
        startActivity(intent)
    }

    private fun getUserAllWriting() {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).getUserAllWritings(JwtConfig.USER_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                writingRVAdapter.update(items.data)
                pbLoadingUser.visibility = View.INVISIBLE
                srlMyPage.isRefreshing = false
            }, {
                Toast.makeText(activity, "게시글을 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
                pbLoadingUser.visibility = View.INVISIBLE
                srlMyPage.isRefreshing = false
            })
    }
}
