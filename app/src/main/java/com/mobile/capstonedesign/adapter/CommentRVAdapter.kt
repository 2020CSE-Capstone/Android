package com.mobile.capstonedesign.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.ReplyDetailActivity
import com.mobile.capstonedesign.adapter.click.BoardClick
import com.mobile.capstonedesign.adapter.click.CommentClick
import com.mobile.capstonedesign.adapter.click.CommentMenuClick
import com.mobile.capstonedesign.adapter.click.CommentReplyClick
import com.mobile.capstonedesign.dto.response.CommentResponseDTO
import kotlinx.android.synthetic.main.comment_form.view.*
import java.text.SimpleDateFormat

class CommentRVAdapter(var context: Context?) :
    RecyclerView.Adapter<CommentRVAdapter.mViewHolder>() {

    private var comments: ArrayList<CommentResponseDTO> = ArrayList()
    var commentClick: CommentClick? = null
    var commentMenuClick: CommentMenuClick? = null
    var commentReplyClick: CommentReplyClick? = null

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nickname: TextView = itemView.tvCommentName
        var content: TextView = itemView.tvCommentContent
        var comment_date: TextView = itemView.tvCommentDate
        var comment_reply: TextView = itemView.tvCommentReply
        var isReply: TextView = itemView.vIfReply
        var comment_menu: ImageView = itemView.ivCommentMenu
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): mViewHolder =
        mViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.comment_form, p0, false))

    override fun onBindViewHolder(p0: mViewHolder, position: Int) {
        p0.nickname.text = comments[position].nickname
        p0.content.text = comments[position].content
        p0.comment_date.text =
            SimpleDateFormat("yyyy.MM.dd. HH:mm").format(comments[position].comment_date)
        p0.isReply.text = comments[position].seq.toString()

        if (comments[position].seq != 1) {
            p0.isReply.layoutParams.width = 200
//            p0.isReply.visibility = View.INVISIBLE
        } else {
            p0.isReply.layoutParams.width = 40
        }

        if (commentClick != null) {
            p0.comment_reply.setOnClickListener { v ->
                commentClick?.onClick(
                    v,
                    position,
                    comments[position].community_board_no,
                    comments[position].parent_comment_no
                )
            }
        }

        if (commentReplyClick != null) {
            p0.comment_reply.setOnClickListener { v ->
                commentReplyClick?.onClick(v, position, comments[position].nickname)
            }
        }

        if (commentMenuClick != null) {
            p0.comment_menu.setOnClickListener { v ->
                commentMenuClick?.onClick(
                    v,
                    position,
                    comments[position].community_board_no,
                    comments[position].parent_comment_no,
                    comments[position].user_id,
                    comments[position].content,
                    comments[position].comment_no
                )
            }
        }
    }

    override fun getItemCount(): Int = comments.size

    fun update(comments: ArrayList<CommentResponseDTO>) {
        this.comments.clear()
        this.comments.addAll(comments)
//        this.comments = comments
        notifyDataSetChanged()
    }
}