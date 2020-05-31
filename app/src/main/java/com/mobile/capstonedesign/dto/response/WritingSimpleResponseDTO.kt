package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class WritingSimpleResponseDTO(
    @SerializedName("board_no") var board_no: Int,
    @SerializedName("title") var title: String,
    @SerializedName("write_date") var write_date: Date,
    @SerializedName("like_count") var like_count: Int,
    @SerializedName("user_id") var user_id: Int,
    @SerializedName("nickname") var nickname: String,
    @SerializedName("comment_count") var comment_count: Int
)