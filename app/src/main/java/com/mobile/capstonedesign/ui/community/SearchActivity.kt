package com.mobile.capstonedesign.ui.community

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.adapter.WritingRVAdapter
import com.mobile.capstonedesign.adapter.click.BoardClick
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_recent_writing.*


class SearchActivity() : AppCompatActivity() {

    private val writingRVAdapter = WritingRVAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.hide()

        ivBack.setOnClickListener {
            finish()
        }

        ivSearch.setOnClickListener {
            getSearchWritings()
        }

        rvSearchWriting.layoutManager = LinearLayoutManager(this)
        rvSearchWriting.adapter = writingRVAdapter
        writingRVAdapter.boardClick = object :
            BoardClick {
            override fun onClick(view: View, position: Int, no: Int, user_id: Int) {
                intentDetail(no, user_id)
            }
        }
    }

    private fun intentDetail(board_no: Int, user_id: Int) {
        val intent = Intent(this, BoardDetailActivity::class.java)
        intent.putExtra("board_no", board_no)
        intent.putExtra("user_id", user_id)
        startActivity(intent)
    }

    private fun getSearchWritings() {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).getSearchWritings(etSearch.text.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                writingRVAdapter.update(items.data)
//                pbLoadingRecent.visibility = View.INVISIBLE
//                srlRecent.isRefreshing = false
                llNoSearch.visibility = View.INVISIBLE
            }, {
                Toast.makeText(this, "게시글을 불러오는 데 실패했습니다. \n"+it.message+"\n"+it.cause, Toast.LENGTH_SHORT).show()
                pbLoadingRecent.visibility = View.INVISIBLE
                srlRecent.isRefreshing = false
                llNoSearch.visibility = View.VISIBLE
            })
//        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
//        val disposable = RecentWritingClient().getApi(BASE_URL).getAllMembers()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { items -> recentWritingRVAdapter.update(items) }

//        btTest.setOnClickListener {
//            val intent = Intent(activity, MainMemberActivity::class.java)
//            startActivity(intent)
//        }
    }

}

