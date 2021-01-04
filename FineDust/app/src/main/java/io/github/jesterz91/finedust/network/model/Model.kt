package io.github.jesterz91.finedust.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FineDust(
    val weather: Weather,
    val common: Common,
    val result: Result
)

@JsonClass(generateAdapter = true)
data class Weather(
    val dust: List<Dust>
)

@JsonClass(generateAdapter = true)
data class Dust(
    val station: Station,
    val timeObservation: String,
    val pm10: Pm10
)

@JsonClass(generateAdapter = true)
data class Station(
    val longitude: String,
    val latitude: String,
    val name: String,
    val id: String
)

@JsonClass(generateAdapter = true)
data class Pm10(
    val grade: String,
    val value: String
)

@JsonClass(generateAdapter = true)
data class Common(
    val alertYn: String,
    val stormYn: String
)

@JsonClass(generateAdapter = true)
data class Result(
    val code: Int,
    val requestUrl: String,
    val message: String
)