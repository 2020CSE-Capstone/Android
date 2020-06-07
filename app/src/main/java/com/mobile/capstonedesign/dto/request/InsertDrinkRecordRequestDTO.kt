package com.mobile.capstonedesign.dto.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class InsertDrinkRecordRequestDTO(
    @SerializedName("figure")
    @Expose
    var figure: Double,

    @SerializedName("glass")
    @Expose
    var glass: Int,

    @SerializedName("drink_date")
    @Expose
    var drink_date: String,

    @SerializedName("drink_name")
    @Expose
    var drink_name: String,

    @SerializedName("user_id")
    @Expose
    var user_id: Int
)