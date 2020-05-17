package com.mobile.capstonedesign.retrofit.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.capstonedesign.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main_member_test.*

class UseLikeThis : AppCompatActivity() {

    private var recyclerViewAdapter: RecentWritingRVAdapter = RecentWritingRVAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_member_test)

        rvRecyclerView.layoutManager = LinearLayoutManager(this)
        rvRecyclerView.adapter = recyclerViewAdapter

        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = RecentWritingClient().getApi(BASE_URL).getAllMembers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> recyclerViewAdapter.update(items) }
    }
}
