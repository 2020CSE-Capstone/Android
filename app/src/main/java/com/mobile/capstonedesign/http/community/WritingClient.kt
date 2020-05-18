package com.mobile.capstonedesign.http.community

import com.mobile.capstonedesign.http.HttpApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WritingClient {

    fun getApi(baseURL :String): HttpApi = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HttpApi::class.java)
}