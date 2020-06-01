package com.mobile.capstonedesign.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mobile.capstonedesign.AlcoholMeasureActivity
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.TobaccoMeasureActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

//    companion object {
//        fun newInstance() = AlcoholPagerFragment()
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnMeasureSubmit.setOnClickListener {
            val intent = Intent(activity, AlcoholMeasureActivity::class.java)
            startActivity(intent)
        }

        btnMeasureSmoke.setOnClickListener {
            val intent = Intent(activity, TobaccoMeasureActivity::class.java)
            startActivity(intent)
        }

//        measure_button.setOnClickListener {
//            val intent = Intent(context, SelectMeasureModeActivity::class.java)
//            startActivity(intent)
//        }

//        init()
    }

//    private fun init() {
//        vpAlcoholChart?.adapter = object : FragmentStateAdapter(this) {
//            override fun createFragment(position: Int): Fragment {
//                return ChartPagerFragment.newInstance()
//            }
//
//            override fun getItemCount(): Int {
//                return 12
//            }
//        }
//
//        TabLayoutMediator(tlAlcoholCalendar, this.vpAlcoholChart) { tab, position ->
//            tab.text = when (position) {
//                0 -> (position + 1).toString() + "월"
//                1 -> (position + 1).toString() + "월"
//                2 -> (position + 1).toString() + "월"
//                3 -> (position + 1).toString() + "월"
//                4 -> (position + 1).toString() + "월"
//                5 -> (position + 1).toString() + "월"
//                6 -> (position + 1).toString() + "월"
//                7 -> (position + 1).toString() + "월"
//                8 -> (position + 1).toString() + "월"
//                9 -> (position + 1).toString() + "월"
//                10 -> (position + 1).toString() + "월"
//                11 -> (position + 1).toString() + "월"
//                else -> (position + 1).toString() + "월"
//            }
//        }.attach()
//    }
}
