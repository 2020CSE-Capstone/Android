package com.mobile.capstonedesign.dto.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class MonthRecordRequestDTO(
    @SerializedName("user_id")
    var user_id: Int,

    @SerializedName("month")
    var month: Int
)