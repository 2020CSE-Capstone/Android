package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.SerializedName
import com.mobile.capstonedesign.dto.response.CommentResponseDTO
import com.mobile.capstonedesign.dto.response.WritingSimpleResponseDTO
import java.util.*

data class ResponseCommentList(
    @SerializedName("status") var status: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: ArrayList<CommentResponseDTO>
)