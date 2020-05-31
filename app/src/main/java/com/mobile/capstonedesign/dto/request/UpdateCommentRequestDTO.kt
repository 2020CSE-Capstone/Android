package com.mobile.capstonedesign.dto.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdateCommentRequestDTO(
    @SerializedName("content")
    @Expose
    var content: String
)