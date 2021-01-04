package io.github.jesterz91.finedust.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.util.*

object GeoUtil {

    fun getLocationFromName(context: Context, city: String, onSuccess: (lat: Double, lon: Double) -> Unit, onError: (String) -> Unit) {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocationName(city, 1)

        if (addresses.isNotEmpty()) {
            val lat = addresses.first().latitude
            val lon = addresses.first().longitude
            onSuccess.invoke(lat, lon)
        } else {
            onError.invoke("주소 결과가 없습니다")
        }
    }
}