package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("status") var status: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: String
)