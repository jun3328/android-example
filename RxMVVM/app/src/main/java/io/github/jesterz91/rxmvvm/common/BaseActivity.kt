package io.github.jesterz91.rxmvvm.common

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {

    abstract val viewModel: VM

    protected val binding by lazy { inflate.invoke(layoutInflater) }

    protected val disposables by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        disposables.clear()
        viewModel.clear()
        super.onDestroy()
    }
}