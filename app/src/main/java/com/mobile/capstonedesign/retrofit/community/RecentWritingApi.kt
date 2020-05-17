package com.mobile.capstonedesign.retrofit.community

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RecentWritingApi {
    @GET("members/")
    fun getAllMembers(): Single<ArrayList<RecentWriting>>

    @GET("members/{idx}")
    fun getMembers(@Path("idx") idx: Int): Single<ArrayList<RecentWriting>>
}