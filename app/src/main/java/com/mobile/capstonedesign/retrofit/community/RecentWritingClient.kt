package com.mobile.capstonedesign.retrofit.community

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RecentWritingClient {

    fun getApi(baseURL :String): RecentWritingApi = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RecentWritingApi::class.java)
}