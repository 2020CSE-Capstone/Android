package com.mobile.capstonedesign.http

import com.mobile.capstonedesign.model.Writing
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HttpApi {
    @GET("community/recent")
    fun getRecentAllWritings(): Single<ArrayList<Writing>>

    @GET("community/like")
    fun getLikeAllWritings(): Single<ArrayList<Writing>>

    @GET("community/{user_id}")
    fun getUserAllWritings(@Path("user_id") user_id: Int): Single<ArrayList<Writing>>

//    @GET("members/{idx}")
//    fun getMembers(@Path("idx") idx: Int): Single<ArrayList<RecentWriting>>
}