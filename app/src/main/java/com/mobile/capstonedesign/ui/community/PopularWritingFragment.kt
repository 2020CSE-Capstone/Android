package com.mobile.capstonedesign.ui.community

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.capstonedesign.BoardDetailActivity

import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.adapter.click.BoardClick
import com.mobile.capstonedesign.http.HttpClient
import com.mobile.capstonedesign.adapter.WritingRVAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_popular_writing.*

class PopularWritingFragment : Fragment() {

    private val writingRVAdapter =
        WritingRVAdapter(context)

    companion object {
        fun newInstance() = PopularWritingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_writing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPopularWriting.layoutManager = LinearLayoutManager(activity)
        rvPopularWriting.adapter = writingRVAdapter
        writingRVAdapter.boardClick = object :
            BoardClick {
            override fun onClick(view: View, position: Int, no: Int) {
                intentDetail(no)
            }
        }

        srlPopular.setOnRefreshListener {
            getLikeAllWriting()
        }

        getLikeAllWriting()
    }

    private fun intentDetail(board_no:Int){
        val intent = Intent(activity, BoardDetailActivity::class.java)
        intent.putExtra("board_no", board_no)
        startActivity(intent)
    }

    private fun getLikeAllWriting(){
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).getLikeAllWritings()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                writingRVAdapter.update(items.data)
                pbLoadingPopular.visibility = View.INVISIBLE
                srlPopular.isRefreshing=false
            }, {
                Toast.makeText(activity, "게시글을 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
                pbLoadingPopular.visibility = View.INVISIBLE
                srlPopular.isRefreshing=false
            })
    }
}
