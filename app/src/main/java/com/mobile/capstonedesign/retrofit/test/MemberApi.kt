package com.mobile.capstonedesign.retrofit.test

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MemberApi {
    @GET("members/")
    fun getAllMembers(): Single<ArrayList<MemberRepo>>

    @GET("members/{idx}")
    fun getMembers(@Path("idx") idx: Int): Single<ArrayList<MemberRepo>>
}