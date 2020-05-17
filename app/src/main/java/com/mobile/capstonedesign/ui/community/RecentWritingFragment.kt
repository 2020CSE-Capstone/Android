package com.mobile.capstonedesign.ui.community

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.retrofit.community.RecentWritingClient
import com.mobile.capstonedesign.retrofit.community.RecentWritingRVAdapter
import com.mobile.capstonedesign.retrofit.test.MainMemberActivity
import com.mobile.capstonedesign.retrofit.test.MemberRecyclerViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recent_writing.*

open class RecentWritingFragment: Fragment() {

    companion object {
        fun newInstance() = RecentWritingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recent_writing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recentWritingRVAdapter: RecentWritingRVAdapter = RecentWritingRVAdapter()

        rvRecentWriting.layoutManager = LinearLayoutManager(activity)
        rvRecentWriting.adapter = recentWritingRVAdapter

        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = RecentWritingClient().getApi(BASE_URL).getAllMembers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> recentWritingRVAdapter.update(items) }

//        btTest.setOnClickListener {
//            val intent = Intent(activity, MainMemberActivity::class.java)
//            startActivity(intent)
//        }
    }
}