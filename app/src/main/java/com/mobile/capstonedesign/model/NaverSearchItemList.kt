package com.mobile.capstonedesign.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mobile.capstonedesign.dto.response.NaverSearchItemResponseDTO
import java.util.*

data class NaverSearchItemList(
    @Expose
    @SerializedName("lastBuildDate") var lastBuildDate: String,
    @Expose
    @SerializedName("total") var total: Int,
    @Expose
    @SerializedName("start") var start: Int,
    @Expose
    @SerializedName("display") var display: Int,
    @Expose
    @SerializedName("items") var items: ArrayList<NaverSearchItemResponseDTO>
)