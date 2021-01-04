package io.github.jesterz91.testapp.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger

abstract class BaseViewModel : ViewModel(), AnkoLogger {

    protected val disposables by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}