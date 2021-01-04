package io.github.jesterz91.finedust.network

import io.github.jesterz91.finedust.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class FineDustInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("appKey", BuildConfig.FINEDUST_APP_KEY)
            .build()
        return chain.proceed(request) // response
    }
}