package com.mobile.capstonedesign.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.dto.response.WritingSimpleResponseDTO
import com.mobile.capstonedesign.adapter.click.ItemClick
import com.mobile.capstonedesign.dto.response.CommentResponseDTO
import kotlinx.android.synthetic.main.comment_form.view.*
import java.text.SimpleDateFormat

class CommentRVAdapter(var context: Context?) :
    RecyclerView.Adapter<CommentRVAdapter.mViewHolder>() {

    private val comments: ArrayList<CommentResponseDTO> = ArrayList()
//    var itemClick: ItemClick? = null

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var user_id: TextView = itemView.tvCommentName
        var content:TextView =itemView.tvCommentContent
        var comment_date:TextView =itemView.tvCommentDate
        var comment_reply:TextView =itemView.tvCommentReply
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): mViewHolder =
        mViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.comment_form, p0, false))

    override fun onBindViewHolder(p0: mViewHolder, position: Int) {
        p0.user_id.text = comments[position].user_id.toString()
        p0.content.text = comments[position].content
        p0.comment_date.text =
            SimpleDateFormat("yyyy.MM.dd. HH:mm").format(comments[position].comment_date)

//        if (itemClick != null) {
//            p0.itemView.setOnClickListener { v ->
//                itemClick?.onClick(v, position, comments[position].board_no)
//            }
//        }

        p0.comment_reply.setOnClickListener {
            Toast.makeText(context, "${comments[position].content}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = comments.size

    fun update(comments: ArrayList<CommentResponseDTO>) {
        this.comments.clear()
        this.comments.addAll(comments)
        notifyDataSetChanged()
    }
}