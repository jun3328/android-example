package io.github.jesterz91.testapp.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{name}")
    fun getUserInfo(@Path("name") name: User): Single<UserInfo>

    @GET("users/{name}/repos")
    fun getUserRepos(@Path("name") name: User): Single<List<UserRepo>>
}