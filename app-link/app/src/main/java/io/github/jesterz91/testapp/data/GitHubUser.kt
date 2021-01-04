package io.github.jesterz91.testapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class GitHubUser

@JsonClass(generateAdapter = true)
data class UserInfo(
    val login: String,
    @Json(name = "avatar_url")
    val avatar: String
) : GitHubUser()

@JsonClass(generateAdapter = true)
data class UserRepo(
    val name: String,
    val description: String?,
    @Json(name = "stargazers_count")
    val star: Int
) : GitHubUser()