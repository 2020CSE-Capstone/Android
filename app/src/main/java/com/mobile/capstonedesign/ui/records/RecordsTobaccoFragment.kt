package com.mobile.capstonedesign.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.capstonedesign.R

class RecordsTobaccoFragment : Fragment() {

    companion object {
        fun newInstance() = RecordsTobaccoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tobacco_records, container, false)

        return view
    }
}