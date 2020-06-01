package com.mobile.capstonedesign.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.dto.response.WritingSimpleResponseDTO
import com.mobile.capstonedesign.adapter.click.BoardClick
import kotlinx.android.synthetic.main.community_writing_simple.view.*
import java.text.SimpleDateFormat

class WritingRVAdapter(var context: Context?) : RecyclerView.Adapter<WritingRVAdapter.mViewHolder>() {

    private val writings: ArrayList<WritingSimpleResponseDTO> = ArrayList()
    var boardClick: BoardClick? = null

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var board_no: TextView = itemView.tvBoardNo
        var title: TextView = itemView.tvWritingTitle
        var write_date: TextView = itemView.tvWrittenDate
        var like_count: TextView = itemView.tvLikeCount
        var nickname: TextView = itemView.tvWriter
        var comment_count :TextView = itemView.tvCommentCount
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): mViewHolder =
        mViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.community_writing_simple, p0, false))

    override fun onBindViewHolder(p0: mViewHolder, position: Int) {
        p0.board_no.text = writings[position].board_no.toString()
        p0.title.text = writings[position].title
        p0.write_date.text = writings[position].write_date
        p0.like_count.text = writings[position].like_count.toString()
        p0.nickname.text = writings[position].nickname
        p0.comment_count.text = writings[position].comment_count.toString()

        if(boardClick != null)
        {
            p0.itemView.setOnClickListener { v ->
                boardClick?.onClick(v, position, writings[position].board_no)
            }
        }
    }

    override fun getItemCount(): Int = writings.size

    fun update(writings: ArrayList<WritingSimpleResponseDTO>) {
        this.writings.clear()
        this.writings.addAll(writings)
        notifyDataSetChanged()
    }
}