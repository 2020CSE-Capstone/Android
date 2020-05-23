package com.mobile.capstonedesign.dto.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InsertWritingRequestDTO(
    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("content")
    @Expose
    var content: String,

    @SerializedName("user_id")
    @Expose
    var user_id: Int
)