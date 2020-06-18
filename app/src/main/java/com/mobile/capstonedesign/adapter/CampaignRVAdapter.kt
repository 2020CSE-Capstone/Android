package com.mobile.capstonedesign.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.dto.response.WritingSimpleResponseDTO
import com.mobile.capstonedesign.adapter.click.BoardClick
import com.mobile.capstonedesign.adapter.click.CampaignClick
import com.mobile.capstonedesign.dto.response.NaverSearchItemResponseDTO
import kotlinx.android.synthetic.main.campaign_item_form.view.*
import kotlinx.android.synthetic.main.community_writing_simple.view.*
import java.text.SimpleDateFormat

class CampaignRVAdapter(var context: Context?) : RecyclerView.Adapter<CampaignRVAdapter.mViewHolder>() {

    private val campaigns: ArrayList<NaverSearchItemResponseDTO> = ArrayList()
    var campaignClick: CampaignClick? = null

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvCampaignTitle: TextView = itemView.tvCampaignTitle
        var tvCampaignContent: TextView = itemView.tvCampaignContent
        var tvCampaignWriter: TextView = itemView.tvCampaignWriter
        var tvCampaignDate: TextView = itemView.tvCampaignDate
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): mViewHolder =
        mViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.campaign_item_form, p0, false))

    override fun onBindViewHolder(p0: mViewHolder, position: Int) {
        p0.tvCampaignTitle.text = Html.fromHtml(campaigns[position].title)
        p0.tvCampaignContent.text = Html.fromHtml(campaigns[position].description)
        p0.tvCampaignWriter.text = campaigns[position].bloggername
        p0.tvCampaignDate.text = campaigns[position].postdate

        if(campaignClick != null)
        {
            p0.itemView.setOnClickListener { v ->
                campaignClick?.onClick(campaigns[position].link)
            }
        }
    }

    override fun getItemCount(): Int = campaigns.size

    fun reload(campaigns: ArrayList<NaverSearchItemResponseDTO>) {
        this.campaigns.addAll(campaigns)
        notifyDataSetChanged()
    }

    fun update(campaigns: ArrayList<NaverSearchItemResponseDTO>) {
        this.campaigns.clear()
        this.campaigns.addAll(campaigns)
        notifyDataSetChanged()
    }
}