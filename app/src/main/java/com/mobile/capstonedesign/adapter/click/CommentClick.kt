package com.mobile.capstonedesign.adapter.click

import android.view.View

interface CommentClick {
    fun onClick(view: View, position: Int, board_no: Int, comment_no:Int)
}