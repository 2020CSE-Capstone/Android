package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class WritingDetailResponseDTO(
    @SerializedName("board_no") var board_no: Int,
    @SerializedName("title") var title: String,
    @SerializedName("content") var content: String,
    @SerializedName("write_date") var write_date: Date,
    @SerializedName("like_count") var like_count: Int,
    @SerializedName("user_id") var user_id: Int
)