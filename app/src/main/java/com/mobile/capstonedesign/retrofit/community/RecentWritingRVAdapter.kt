package com.mobile.capstonedesign.retrofit.community

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.capstonedesign.R
import kotlinx.android.synthetic.main.recyclerview_member_test.view.*

class RecentWritingRVAdapter : RecyclerView.Adapter<RecentWritingRVAdapter.mViewHolder>() {

    private val recentWritings: ArrayList<RecentWriting> = ArrayList()

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.tvID
        var email: TextView = itemView.tvEmail
        var name: TextView = itemView.tvName
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): mViewHolder =
        mViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.recyclerview_member_test, p0, false))

    override fun onBindViewHolder(p0: mViewHolder, p1: Int) {
        p0.id.text = recentWritings[p1].id
        p0.email.text = recentWritings[p1].email
        p0.name.text = recentWritings[p1].name
    }

    override fun getItemCount(): Int = recentWritings.size

    fun update(recentWritings: ArrayList<RecentWriting>) {
        this.recentWritings.clear()
        this.recentWritings.addAll(recentWritings)
        notifyDataSetChanged()
    }

}