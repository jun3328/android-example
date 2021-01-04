package io.github.jesterz91.finedust.ui.home

import io.github.jesterz91.finedust.common.BaseContract
import io.github.jesterz91.finedust.ui.home.adapter.HomeAdapterContract
import io.github.jesterz91.finedust.ui.home.model.DustInfo

interface HomeContract {

    interface View : BaseContract.View {
        fun lastLocationFetched()
    }

    interface Presenter : BaseContract.Presenter {

        val adapterView: HomeAdapterContract.AdapterView
        val adapterModel: HomeAdapterContract.AdapterModel

        fun requestLastLocation()
        fun fetchSavedLocation()
        fun saveLocation(city: String, lat: Double, lon: Double)
        fun getItem(position: Int): DustInfo
    }
}