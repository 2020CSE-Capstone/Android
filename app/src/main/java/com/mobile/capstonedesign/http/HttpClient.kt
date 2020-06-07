package com.mobile.capstonedesign.http

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException

class HttpClient {

    var gson = GsonBuilder()
    .setLenient()
    .create();

    fun getApi(baseURL :String): HttpApi = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HttpApi::class.java)

    fun getOpenApi(baseURL :String): HttpApi = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(OkHttpClient())
//        .client(provideOkHttpClient(AppInterceptor()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HttpApi::class.java)


    private fun provideOkHttpClient(
        interceptor: AppInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor)
            build()
        }

    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain)
                : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("X-Naver-Client-Id", "0PWYBt9FGM0TOjx2x_K0")
                .addHeader("X-Naver-Client-Secret", "1Ngct36j7u")
                .build()

            proceed(newRequest)
        }
    }
}