package com.mobile.capstonedesign.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mobile.capstonedesign.R

class RecordsFragment : Fragment() {

    private lateinit var recordsViewModel: RecordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recordsViewModel =
                ViewModelProviders.of(this).get(RecordsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_records, container, false)
        val textView: TextView = root.findViewById(R.id.text_records)
        recordsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
