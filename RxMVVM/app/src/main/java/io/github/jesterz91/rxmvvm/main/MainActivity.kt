package io.github.jesterz91.rxmvvm.main

import android.os.Bundle
import io.github.jesterz91.rxmvvm.common.BaseActivity
import io.github.jesterz91.rxmvvm.databinding.ActivityMainBinding
import io.github.jesterz91.rxmvvm.extension.toObservable
import io.reactivex.rxkotlin.addTo

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    override val viewModel: MainViewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        viewModel.query.toObservable()
            .subscribe(::println)
            .addTo(disposables)
    }
}
