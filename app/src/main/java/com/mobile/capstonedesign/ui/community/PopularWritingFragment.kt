package com.mobile.capstonedesign.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.http.community.WritingClient
import com.mobile.capstonedesign.http.community.WritingRVAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_popular_writing.*

class PopularWritingFragment : Fragment() {

    companion object {
        fun newInstance() = PopularWritingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_writing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recentWritingRVAdapter = WritingRVAdapter()

        rvPopularWriting.layoutManager = LinearLayoutManager(activity)
        rvPopularWriting.adapter = recentWritingRVAdapter

        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = WritingClient().getApi(BASE_URL).getLikeAllWritings()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> recentWritingRVAdapter.update(items) }
    }
}
