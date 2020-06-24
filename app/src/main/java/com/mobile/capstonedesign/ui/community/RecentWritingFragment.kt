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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recent_writing.*

open class RecentWritingFragment : Fragment() {

    private val writingRVAdapter = WritingRVAdapter(context)

    companion object {
        fun newInstance() = RecentWritingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recent_writing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvRecentWriting.layoutManager = LinearLayoutManager(activity)
        rvRecentWriting.adapter = writingRVAdapter
        writingRVAdapter.boardClick = object :
            BoardClick {
            override fun onClick(view: View, position: Int, no: Int, user_id: Int) {
                intentDetail(no, user_id)
            }
        }

        srlRecent.setOnRefreshListener {
            getRecentAllWriting()
        }

        getRecentAllWriting()
    }

    private fun intentDetail(board_no: Int, user_id: Int) {
        val intent = Intent(activity, BoardDetailActivity::class.java)
        intent.putExtra("board_no", board_no)
        intent.putExtra("user_id", user_id)
        startActivity(intent)
    }

    private fun getRecentAllWriting() {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).getRecentAllWritings()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                writingRVAdapter.update(items.data)
                pbLoadingRecent.visibility = View.INVISIBLE
                srlRecent.isRefreshing = false
            }, {
                Toast.makeText(activity, "게시글을 불러오는 데 실패했습니다. \n"+it.message+"\n"+it.cause, Toast.LENGTH_SHORT).show()
                pbLoadingRecent.visibility = View.INVISIBLE
                srlRecent.isRefreshing = false
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