package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class MonthRecordResponseDTO(
    @SerializedName("date")
    var date: String,

    @SerializedName("total_amount")
    var total_amount: Int
)