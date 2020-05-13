package com.mobile.capstonedesign

import com.mobile.capstonedesign.dto.response.MemberDetailResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ReadMemberService {
    @GET("/members/2")
    fun listRepos(@Path("idx") idx:Int): Call<MemberDetailResponseDTO>
}