package com.mobile.capstonedesign.dto.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class NaverSearchItemResponseDTO(
    @SerializedName("title") var title: String,
    @SerializedName("link") var link: String,
    @SerializedName("description") var description: String,
    @SerializedName("bloggername") var bloggername: String,
    @SerializedName("bloggerlink") var bloggerlink: String,
    @SerializedName("postdate") var postdate: String
 )