package com.mobile.capstonedesign.adapter.click

import android.view.View

interface CommentReplyClick {
    fun onClick(view: View, position: Int, nickname: String)
}