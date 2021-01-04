package io.github.jesterz91.finedust.ui.home

import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import io.github.jesterz91.finedust.common.BasePresenter
import io.github.jesterz91.finedust.database.Location
import io.github.jesterz91.finedust.database.LocationDao
import io.github.jesterz91.finedust.network.FineDustService
import io.github.jesterz91.finedust.network.model.FineDust
import io.github.jesterz91.finedust.ui.home.adapter.HomeAdapterContract
import io.github.jesterz91.finedust.ui.home.model.DustInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomePresenter(
    view: HomeContract.View,
    override val adapterView: HomeAdapterContract.AdapterView,
    override val adapterModel: HomeAdapterContract.AdapterModel,
    private val locationDao: LocationDao,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : BasePresenter<HomeContract.View>(view), HomeContract.Presenter {

    private fun getFineDust(lat: Double, lon: Double, name: String, isCurrent: Boolean = false) {
        FineDustService.api.getFineDust(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress(true) }
            .doAfterTerminate { view.showProgress(false) }
            .subscribe({fineDust ->
                handleResponse(name, fineDust, isCurrent)
            }, { error ->
                handleError(error)
            }).addTo(disposables)
    }

    private fun handleResponse(name: String, fineDust: FineDust, isCurrent: Boolean = false) {
        if (fineDust.weather.dust.isEmpty()) {
            Log.e(TAG, "dust info empty")
            return
        }
        val (station, time, pm10) = fineDust.weather.dust.first()
        val fineDustInfo =
            DustInfo(
                name,
                station.name,
                time,
                pm10.value,
                pm10.grade,
                station.latitude,
                station.longitude
            )

        adapterModel.addItem(fineDustInfo)

        adapterView.refresh()

        if (isCurrent) view.lastLocationFetched()
    }

    override fun requestLastLocation() {
        Log.e(TAG, "requestLastLocation")
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                Log.e(TAG, "addOnSuccessListener")
                if (location != null) {
                    Log.e(TAG, "마지막 위치 : $location")
                    getFineDust(location.latitude, location.longitude, "현재위치", true)
                } else {
                    adapterModel.addItem(
                        DustInfo(
                            "현재위치"
                        )
                    )
                    adapterView.refresh()
                }
            }.addOnFailureListener {
                Log.e(TAG, "addOnFailureListener")
                adapterModel.addItem(
                    DustInfo(
                        "현재위치"
                    )
                )
                adapterView.refresh()
            }
    }

    override fun fetchSavedLocation() {
        launch {
            val locations = async(Dispatchers.IO) { locationDao.getLocations() }
            locations.await().forEach {
                getFineDust(it.lat, it.lon, it.name)
            }
        }
    }

    override fun saveLocation(city: String, lat: Double, lon: Double) {
        view.showToast("도시는 $city, 위치는 $lat, $lon")
        launch(Dispatchers.IO) {
            locationDao.insertRepo(Location(name = city, lat = lat, lon = lon))
            locationDao.getLocations().forEach {
                Log.i(TAG, it.toString())
            }
        }
    }


    override fun getItem(position: Int): DustInfo = adapterModel.getItem(position)
}