package io.github.jesterz91.finedust.ui.weather

import io.github.jesterz91.finedust.common.BasePresenter

class WeatherPresenter(view: WeatherContract.View): BasePresenter<WeatherContract.View>(view), WeatherContract.Presenter {

    override fun request() {
        view.loadUrl("https://weatherplanet.co.kr")
//        view.loadUrl("https://developer.android.com/")
    }
}