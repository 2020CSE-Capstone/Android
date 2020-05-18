package com.mobile.capstonedesign.http.test

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MemberClient {

    fun getApi(baseURL :String): MemberApi = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MemberApi::class.java)
}