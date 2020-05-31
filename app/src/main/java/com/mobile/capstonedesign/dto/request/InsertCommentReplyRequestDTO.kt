package com.mobile.capstonedesign.dto.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InsertCommentReplyRequestDTO(
    @SerializedName("content")
    @Expose
    var content: String,

    @SerializedName("parent_comment_no")
    @Expose
    var parent_comment_no: Int,

    @SerializedName("user_id")
    @Expose
    var user_id: Int,

    @SerializedName("community_board_no")
    @Expose
    var community_board_no: Int
)