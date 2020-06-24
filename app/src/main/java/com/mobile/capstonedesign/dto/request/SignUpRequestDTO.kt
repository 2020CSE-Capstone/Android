package com.mobile.capstonedesign.dto.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class SignUpRequestDTO(
    @SerializedName("email")
    @Expose
    var email: String?,

    @SerializedName("nickname")
    @Expose
    var nickname: String?,

    @SerializedName("password")
    @Expose
    var password: String?,

    @SerializedName("drink_average")
    @Expose
    var drink_average: Int?,

    @SerializedName("smoke_average")
    @Expose
    var smoke_average: Int?,

    @SerializedName("determination")
    @Expose
    var determination: String?
)