package com.mobile.capstonedesign.ui.campaign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.api.SearchBlogAPI
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recent_writing.*

class CampaignFragment : Fragment() {

    private lateinit var campaignViewModel: CampaignViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        campaignViewModel =
            ViewModelProviders.of(this).get(CampaignViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_campaign, container, false)
//        val textView: TextView = root.findViewById(R.id.text_campaign)
        campaignViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val thread = Thread {
//            var apiExamSearchBlog = SearchBlogAPI()
//            apiExamSearchBlog.main()
//        }.start()

        getStopSmokeBlog(10,20)
    }

    private fun getStopSmokeBlog(start: Int, display: Int) {
        val BASE_URL = resources.getString(R.string.naver_search_api_url) // 네이버 검색 api url
//        val header: HashMap<String, String> = HashMap()
//        header.put("X-Naver-Client-Id", "0PWYBt9FGM0TOjx2x_K0")
//        header.put("X-Naver-Client-Secret", "1Ngct36j7u")
        val disposable =
            HttpClient().getOpenApi(BASE_URL).getStopSmokeBlog(/*header,*/"0PWYBt9FGM0TOjx2x_K0", "1Ngct36j7u", "영화", start, display)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    Toast.makeText(activity, "금연글을 불러오는 데 성공했습니다. \n"+items.total, Toast.LENGTH_SHORT).show()
//                writingRVAdapter.update(items.data)
//                pbLoadingRecent.visibility = View.INVISIBLE
//                srlRecent.isRefreshing = false
                }, {
                    Toast.makeText(
                        activity,
                        "금연글을 불러오는 데 실패했습니다. \n" + it.message + "\n" + it.cause,
                        Toast.LENGTH_SHORT
                    ).show()
//                pbLoadingRecent.visibility = View.INVISIBLE
//                srlRecent.isRefreshing = false
                })
    }
}
