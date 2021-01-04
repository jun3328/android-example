package io.github.jesterz91.finedust.network

import io.github.jesterz91.finedust.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object FineDustService {

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(FineDustInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }).build()
    }

    val api: FineDustApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.weatherplanet.co.kr/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(FineDustApi::class.java)
    }
}