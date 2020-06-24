package com.mobile.capstonedesign.ui.campaign

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.adapter.CampaignRVAdapter
import com.mobile.capstonedesign.adapter.click.CampaignClick
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_campaign_smoke.*

class CampaignSmokeFragment : Fragment() {

    private val campaignRVAdapter = CampaignRVAdapter(context)
    private var index = 1

    companion object {
        fun newInstance() = CampaignSmokeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_campaign_smoke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCampaignSmoke.layoutManager = LinearLayoutManager(activity)
        rvCampaignSmoke.adapter = campaignRVAdapter
        campaignRVAdapter.campaignClick = object :
            CampaignClick {
            override fun onClick(link: String, title: String) {
                val intent = Intent(activity, CampaignWebViewActivity::class.java)
                intent.putExtra("link", link)
                intent.putExtra("title", title)
                startActivity(intent)
            }
        }

        nsvCampaignSmoke.setOnScrollChangeListener { _, _, _, _, _ ->
            if (!nsvCampaignSmoke.canScrollVertically(1)) {
                Toast.makeText(activity, "더 불러오기", Toast.LENGTH_SHORT).show()
                index += 20
                getMoreStopSmokeBlog(index, 20)
            }
        }

        srlCampaignSmoke.setOnRefreshListener {
            index = 1
            getStopSmokeBlog(20)
        }

        getStopSmokeBlog(20)
    }

    private fun getStopSmokeBlog(display: Int) {
        val BASE_URL = resources.getString(R.string.naver_search_api_url) // 네이버 검색 api url
        val disposable =
            HttpClient().getOpenApi(BASE_URL)
                .getNaverSearchBlog("0PWYBt9FGM0TOjx2x_K0", "1Ngct36j7u", "금연", 1, display)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    Toast.makeText(activity, "금연 검색 성공", Toast.LENGTH_SHORT).show()
                    campaignRVAdapter.update(items.items)
                    srlCampaignSmoke.isRefreshing = false
                }, {
                    Toast.makeText(activity, "금연 검색 실패", Toast.LENGTH_SHORT).show()
                    srlCampaignSmoke.isRefreshing = false
                })
    }

    private fun getMoreStopSmokeBlog(start: Int, display: Int) {
        val BASE_URL = resources.getString(R.string.naver_search_api_url) // 네이버 검색 api url
        val disposable =
            HttpClient().getOpenApi(BASE_URL)
                .getNaverSearchBlog("0PWYBt9FGM0TOjx2x_K0", "1Ngct36j7u", "금연", start, display)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    Toast.makeText(activity, "금연 검색 성공", Toast.LENGTH_SHORT).show()
                    campaignRVAdapter.reload(items.items)
                    srlCampaignSmoke.isRefreshing = false
                }, {
                    Toast.makeText(activity, "금연 검색 실패", Toast.LENGTH_SHORT).show()
                    srlCampaignSmoke.isRefreshing = false
                })
    }
}