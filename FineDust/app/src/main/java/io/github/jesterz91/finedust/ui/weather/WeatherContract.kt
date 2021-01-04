package io.github.jesterz91.finedust.ui.weather

import io.github.jesterz91.finedust.common.BaseContract

interface WeatherContract {

    interface View : BaseContract.View {
        fun loadUrl(url: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun request()
    }
}