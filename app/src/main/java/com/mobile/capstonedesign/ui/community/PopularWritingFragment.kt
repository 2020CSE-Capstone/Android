package com.mobile.capstonedesign.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mobile.capstonedesign.R

class PopularWritingFragment : Fragment() {

    companion object {
        fun newInstance() = PopularWritingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_writing, container, false)
    }

}
