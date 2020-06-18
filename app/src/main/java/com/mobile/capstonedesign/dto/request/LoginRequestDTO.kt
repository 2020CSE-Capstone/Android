package com.mobile.capstonedesign.dto.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequestDTO(
    @SerializedName("email")
    @Expose
    var username: String,

    @Expose
    @SerializedName("password")
    var password: String
)