package com.mobile.capstonedesign.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Comment(
    @SerializedName("comment_no") var comment_no: Int,
    @SerializedName("content") var content: String,
    @SerializedName("parent_comment_no") var parent_comment_no: String,
    @SerializedName("seq") var seq: Date,
    @SerializedName("comment_date") var comment_date: Int,
    @SerializedName("user_id") var user_id: Int,
    @SerializedName("community_board_no") var community_board_no: Int
)