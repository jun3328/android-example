package io.github.jesterz91.rxmvvm.util

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.Observable as RxObservable

class RxField<T> : ObservableField<T> {

    private val observable: RxObservable<T>

    private val disposableMap: MutableMap<Int, Disposable> = hashMapOf()

    constructor(observable: RxObservable<T>) : super() {
        this.observable = observable
    }

    constructor(observable: RxObservable<T>, defaultValue: T) : super(defaultValue) {
        this.observable = observable
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        super.addOnPropertyChangedCallback(callback)
        disposableMap[callback.hashCode()] = observable.subscribe { super.set(it) }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        disposableMap.run {
            get(callback.hashCode())?.takeUnless(Disposable::isDisposed)?.dispose()
            remove(callback.hashCode())
        }
        super.removeOnPropertyChangedCallback(callback)
    }

    fun tObservable(): RxObservable<T> = observable

    @Deprecated("read only", ReplaceWith("don't use"))
    override fun set(value: T) = throw UnsupportedOperationException("read only")

}