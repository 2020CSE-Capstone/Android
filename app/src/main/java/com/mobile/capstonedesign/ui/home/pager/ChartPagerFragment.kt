package com.mobile.capstonedesign.ui.home.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.capstonedesign.R
import kotlinx.android.synthetic.main.fragment_home.*

open class ChartPagerFragment: Fragment() {

    companion object {
        fun newInstance() = ChartPagerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_chart, container, false)

        return view
    }
}