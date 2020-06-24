package com.mobile.capstonedesign.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.adapter.DrinkRecordRVAdapter
import com.mobile.capstonedesign.adapter.SmokeRecordRVAdapter
import com.mobile.capstonedesign.config.JwtConfig
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tobacco_records.*

class RecordsTobaccoFragment : Fragment() {

    private val recordRVAdapter = SmokeRecordRVAdapter(context)

    companion object {
        fun newInstance() = RecordsTobaccoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tobacco_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvSmokeRecords.layoutManager = LinearLayoutManager(activity)
        rvSmokeRecords.adapter = recordRVAdapter

        getAllSmokeRecords()
    }

    private fun getAllSmokeRecords() {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).getAllSmokeRecords(JwtConfig.USER_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                Toast.makeText(activity, items.status.toString()+"\n"+items.message, Toast.LENGTH_SHORT).show()
                recordRVAdapter.update(items.data)
//                pbLoadingRecent.visibility = View.INVISIBLE
//                srlRecent.isRefreshing = false
            }, {
                Toast.makeText(activity, "기록 리스트를 불러오는 데 실패했습니다. \n"+it.message+"\n"+it.cause, Toast.LENGTH_SHORT).show()
//                pbLoadingRecent.visibility = View.INVISIBLE
//                srlRecent.isRefreshing = false
            })
    }
}