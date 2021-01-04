package io.github.jesterz91.finedust.ui.weather

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import io.github.jesterz91.finedust.R
import io.github.jesterz91.finedust.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : BaseFragment<WeatherContract.Presenter>(), WeatherContract.View {

    override val presenter: WeatherContract.Presenter by lazy { WeatherPresenter(this) }

    override val layoutResId: Int = R.layout.fragment_weather

    override fun showProgress(value: Boolean) {
        showToast("$value")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.request()
    }

    override fun loadUrl(url: String) {
        showToast("onLoad")
        webView.settings.apply {
            javaScriptEnabled = true // 자바스크립트 허용 여부
            javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
            domStorageEnabled = true // 로컬저장소 허용 여부
            useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
            loadWithOverviewMode = true // 메타태그 허용 여부
            builtInZoomControls = false // 화면 확대 축소 허용 여부
            cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
            layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING// 컨텐츠 사이즈 맞추기
            setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
            setSupportZoom(true) // 화면 줌 허용 여부
        }
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }
}