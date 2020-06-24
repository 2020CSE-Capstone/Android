package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseRecordDrinkTotal(
    @SerializedName("status") var status: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: RecordDrinkTotalResponseDTO
)