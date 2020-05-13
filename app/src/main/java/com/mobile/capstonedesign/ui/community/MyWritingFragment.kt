package com.mobile.capstonedesign.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mobile.capstonedesign.R

class MyWritingFragment : Fragment() {

    companion object {
        fun newInstance() = MyWritingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_writing, container, false)
    }

}
