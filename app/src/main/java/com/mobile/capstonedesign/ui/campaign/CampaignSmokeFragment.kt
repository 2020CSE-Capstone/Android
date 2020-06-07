package com.mobile.capstonedesign.ui.campaign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.capstonedesign.R

class CampaignSmokeFragment : Fragment() {

    companion object {
        fun newInstance() = CampaignSmokeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_campaign_smoke, container, false)
    }

}