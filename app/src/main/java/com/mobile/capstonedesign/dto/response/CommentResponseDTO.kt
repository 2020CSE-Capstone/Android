package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class CommentResponseDTO(
    @SerializedName("comment_no") var comment_no: Int,
    @SerializedName("content") var content: String,
    @SerializedName("parent_comment_no") var parent_comment_no: Int,
    @SerializedName("seq") var seq: Int,
    @SerializedName("comment_date") var comment_date: String,
    @SerializedName("user_id") var user_id: Int,
    @SerializedName("community_board_no") var community_board_no: Int,
    @SerializedName("nickname") var nickname: String
)