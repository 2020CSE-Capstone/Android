package com.mobile.capstonedesign.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Writing(
    @SerializedName("board_no") var board_no: Int,
    @SerializedName("title") var title: String,
    @SerializedName("content") var content: String,
    @SerializedName("write_date") var write_date: Date,
    @SerializedName("like_count") var like_count: Int,
    @SerializedName("user_id") var user_id: Int,
    @SerializedName("comment_count") var comment_count: Int
)