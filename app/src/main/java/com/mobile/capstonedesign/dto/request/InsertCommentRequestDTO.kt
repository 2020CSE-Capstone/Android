package com.mobile.capstonedesign.dto.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InsertCommentRequestDTO(
    @SerializedName("content")
    @Expose
    var content: String,

    @SerializedName("user_id")
    @Expose
    var user_id: Int,

    @SerializedName("community_board_no")
    @Expose
    var community_board_no: Int
)