package io.github.jesterz91.rxmvvm.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{name}")
    fun getUserInfo(@Path("name") userName: String): Single<UserResponse>
}