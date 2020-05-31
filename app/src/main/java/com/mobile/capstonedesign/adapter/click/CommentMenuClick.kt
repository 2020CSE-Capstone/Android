package com.mobile.capstonedesign.adapter.click

import android.view.View

interface CommentMenuClick {
    fun onClick(
        view: View,
        position: Int,
        board_no: Int,
        parent_comment_no: Int,
        user_id: Int,
        content: String,
        comment_no: Int
    )
}