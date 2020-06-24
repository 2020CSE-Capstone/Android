package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecordDrinkTotalResponseDTO(
    @SerializedName("total_glass") var totalGlass: Int,
    @SerializedName("total_price") var totalPrice: Int
)