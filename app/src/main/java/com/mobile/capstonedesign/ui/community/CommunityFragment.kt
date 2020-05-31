package com.mobile.capstonedesign.ui.community

import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.capstonedesign.CommunityWritingActivity
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.R.font.notosans
import kotlinx.android.synthetic.main.fragment_community.*


class CommunityFragment : Fragment() {

    private lateinit var communityViewModel: CommunityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        communityViewModel = ViewModelProviders.of(this).get(CommunityViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_community, container, false)
//        val textView: TextView = root.findViewById(R.id.text_community)
        communityViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        setHasOptionsMenu(true)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.community_write -> {
                    Toast.makeText(activity, "글 쓰기", Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, CommunityWritingActivity::class.java)
                    startActivity(intent)
                }
                R.id.community_search -> {
                    Toast.makeText(activity, "글 찾기", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        app_bar_image.setColorFilter(R.color.colorDim)

        init()
    }

    private fun init() {
        vpWriting?.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> RecentWritingFragment.newInstance()
                    1 -> PopularWritingFragment.newInstance()
                    else -> MyWritingFragment.newInstance()
                }
            }

            override fun getItemCount(): Int {
                return 3
            }
        }

        TabLayoutMediator(tlCommunity, this.vpWriting) { tab, position ->
            tab.text = when (position) {
                0 -> "최신글 보기"
                1 -> "인기글 보기"
                else -> "내 글 보기"
            }
        }.attach()
    }
}
