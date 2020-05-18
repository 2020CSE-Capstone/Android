package com.mobile.capstonedesign.http.test

import com.google.gson.annotations.SerializedName

data class MemberRepo(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String
)