package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.SerializedName

data class RecordResponseDTO(
    @SerializedName("id") var id: Int,
    @SerializedName("figure") var figure: Double,
    @SerializedName("glass") var glass: Int,
    @SerializedName("drink_date") var drink_date: String,
    @SerializedName("drink_name") var drink_name: String,
    @SerializedName("user_id") var user_id: Int
)