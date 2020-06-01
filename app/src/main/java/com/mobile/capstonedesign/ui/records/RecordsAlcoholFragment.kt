package com.mobile.capstonedesign.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.capstonedesign.R

class RecordsAlcoholFragment : Fragment() {

    companion object {
        fun newInstance() = RecordsAlcoholFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_alcohol_records, container, false)

        return view
    }
}