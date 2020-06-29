package com.mobile.capstonedesign.http

import com.google.gson.GsonBuilder
import com.mobile.capstonedesign.config.JwtConfig
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

    fun getApi(baseURL: String): HttpApi = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(OkHttpClient())
        .client(provideOkHttpClient(AppInterceptor()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
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
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader(JwtConfig.HEADER_STRING, JwtConfig.TOKEN)
                .build()

            proceed(newRequest)
        }
    }
}