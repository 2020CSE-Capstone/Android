package com.mobile.capstonedesign.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.adapter.click.CommentClick
import com.mobile.capstonedesign.adapter.click.CommentReplyClick
import com.mobile.capstonedesign.dto.response.CommentResponseDTO
import kotlinx.android.synthetic.main.comment_form.view.*
import kotlinx.android.synthetic.main.comment_form.view.tvCommentContent
import kotlinx.android.synthetic.main.comment_form.view.tvCommentDate
import kotlinx.android.synthetic.main.comment_form.view.tvCommentName
import kotlinx.android.synthetic.main.comment_form.view.tvCommentReply
import kotlinx.android.synthetic.main.comment_form.view.vIfReply
import kotlinx.android.synthetic.main.comment_reply_form.view.*

class CommentReplyRVAdapter(var context: Context?) :
    RecyclerView.Adapter<CommentReplyRVAdapter.mViewHolder>() {

    private var comments: ArrayList<CommentResponseDTO> = ArrayList()
    var commentClick: CommentClick? = null
    var commentReplyClick: CommentReplyClick? = null

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nickname: TextView = itemView.tvCommentName
        var content: TextView = itemView.tvCommentContent
        var comment_date: TextView = itemView.tvCommentDate
        var comment_reply: TextView = itemView.tvCommentReply
        var isReply: ImageView = itemView.vIfReply
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): mViewHolder =
        mViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.comment_reply_form, p0, false))

    override fun onBindViewHolder(p0: mViewHolder, position: Int) {
        p0.nickname.text = comments[position].nickname
        p0.content.text = comments[position].content
        p0.comment_date.text = comments[position].comment_date
//        p0.comment_date.text = SimpleDateFormat("yyyy.MM.dd. HH:mm").format(comments[position].comment_date)
//        p0.isReply.text = comments[position].seq.toString()

        if (comments[position].seq == 1) {
            p0.isReply.visibility = View.GONE
//            p0.isReply.visibility = View.INVISIBLE
        } else {
            p0.isReply.visibility = View.VISIBLE
        }

        if (comments[position].del_flag == 1) {
            p0.comment_date.visibility = View.GONE
            p0.comment_reply.visibility = View.GONE
            p0.nickname.visibility = View.GONE
            p0.content.text = "삭제된 댓글입니다."
        } else {
            p0.comment_date.visibility = View.VISIBLE
            p0.comment_reply.visibility = View.VISIBLE
            p0.nickname.visibility = View.VISIBLE
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
    }

    override fun getItemCount(): Int = comments.size

    fun update(comments: ArrayList<CommentResponseDTO>) {
        this.comments.clear()
        this.comments.addAll(comments)
//        this.comments = comments
        notifyDataSetChanged()
    }
}