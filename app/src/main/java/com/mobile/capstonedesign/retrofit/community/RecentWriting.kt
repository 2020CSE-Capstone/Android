package com.mobile.capstonedesign.retrofit.community

import com.google.gson.annotations.SerializedName

data class RecentWriting(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String
)