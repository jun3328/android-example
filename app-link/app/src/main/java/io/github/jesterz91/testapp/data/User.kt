package io.github.jesterz91.testapp.data

import com.squareup.moshi.Json

enum class User {
    @Json(name = "google")
    GOOGLE,
    @Json(name = "android")
    ANDROID,
    @Json(name = "flutter")
    FLUTTER,
    @Json(name = "JakeWharton")
    JAKE,
    @Json(name = "florina-muntenescu")
    FLORINA
}