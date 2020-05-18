package com.mobile.capstonedesign.ui.campaign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mobile.capstonedesign.R

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
}
