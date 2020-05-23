package com.mobile.capstonedesign.dto.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdateWritingRequestDTO(
    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("content")
    @Expose
    var content: String
)