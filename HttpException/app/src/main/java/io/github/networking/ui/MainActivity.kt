package io.github.networking.ui

import android.os.Bundle
import com.jakewharton.rxbinding3.view.clicks
import io.github.networking.api.NetworkService
import io.github.networking.api.ResponseCode
import io.github.networking.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import retrofit2.HttpException

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.button.clicks()
                .flatMapSingle { NetworkService.api.request(ResponseCode.values().random()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::println, ::handleError)
                .addTo(disposables)
    }

    private fun handleError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                showToast("응답코드 : ${throwable.code()}")
            }
        }
    }
}

