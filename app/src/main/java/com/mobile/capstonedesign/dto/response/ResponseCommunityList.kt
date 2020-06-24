package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.SerializedName
import com.mobile.capstonedesign.dto.response.WritingSimpleResponseDTO
import java.util.*

data class ResponseCommunityList(
    @SerializedName("status") var status: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: ArrayList<WritingSimpleResponseDTO>
)