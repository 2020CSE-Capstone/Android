package com.mobile.capstonedesign.http.community

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.model.Writing
import kotlinx.android.synthetic.main.community_writing_simple.view.*
import java.text.SimpleDateFormat

class WritingRVAdapter : RecyclerView.Adapter<WritingRVAdapter.mViewHolder>() {

    private val writings: ArrayList<Writing> = ArrayList()

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.tvWritingTitle
        var content: TextView = itemView.tvWritingContent
        var write_date: TextView = itemView.tvWrittenDate
        var like_count: TextView = itemView.tvLikeCount
        var user_id: TextView = itemView.tvWriter
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): mViewHolder =
        mViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.community_writing_simple, p0, false))

    override fun onBindViewHolder(p0: mViewHolder, p1: Int) {
        p0.title.text = writings[p1].title
        p0.content.text = writings[p1].content
        p0.write_date.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(writings[p1].write_date)
        p0.like_count.text = writings[p1].like_count.toString()
        p0.user_id.text = writings[p1].user_id.toString()
    }

    override fun getItemCount(): Int = writings.size

    fun update(writings: ArrayList<Writing>) {
        this.writings.clear()
        this.writings.addAll(writings)
        notifyDataSetChanged()
    }

}