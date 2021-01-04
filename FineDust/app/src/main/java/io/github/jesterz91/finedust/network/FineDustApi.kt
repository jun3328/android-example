package io.github.jesterz91.finedust.network

import io.github.jesterz91.finedust.network.model.FineDust
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FineDustApi {

    @GET("weather/dust")
    fun getFineDust(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("version") versionCode : Int = 1
    ) : Single<FineDust>
}