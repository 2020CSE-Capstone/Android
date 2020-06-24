package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecordSmokeTotalResponseDTO(
    @SerializedName("total_piece") var totalPiece: Int,
    @SerializedName("total_price") var totalPrice: Int
)