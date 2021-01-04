package io.github.jesterz91.rxmvvm.common

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val disposables by lazy { CompositeDisposable() }

    fun clear() = disposables.clear()
}
