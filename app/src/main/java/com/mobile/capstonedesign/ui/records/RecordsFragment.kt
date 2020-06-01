package com.mobile.capstonedesign.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.capstonedesign.R
import kotlinx.android.synthetic.main.fragment_records.*

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
//        val textView: TextView = root.findViewById(R.id.text_records)
        recordsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        vpRecords?.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when(position){
                    0-> RecordsAlcoholFragment.newInstance()
                    else-> RecordsTobaccoFragment.newInstance()
                }
            }

            override fun getItemCount(): Int {
                return 2
            }
        }

        TabLayoutMediator(tlRecords, this.vpRecords) { tab, position ->
            tab.text = when (position) {
                0 -> "음주"
                else -> "흡연"
            }
        }.attach()
    }
}
