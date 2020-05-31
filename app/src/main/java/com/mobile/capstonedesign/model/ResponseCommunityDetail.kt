package com.mobile.capstonedesign.model

import com.google.gson.annotations.SerializedName
import com.mobile.capstonedesign.dto.response.WritingDetailResponseDTO
import com.mobile.capstonedesign.dto.response.WritingSimpleResponseDTO
import java.util.*

data class ResponseCommunityDetail(
    @SerializedName("status") var status: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: WritingDetailResponseDTO
)