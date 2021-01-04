package io.github.networking.api

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("{code}")
    fun request(@Path("code") code: ResponseCode): Single<ResponseBody>
}