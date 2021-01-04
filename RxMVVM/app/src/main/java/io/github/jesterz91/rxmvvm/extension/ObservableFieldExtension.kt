package io.github.jesterz91.rxmvvm.extension

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposables
import io.reactivex.Observable as RxObservable

fun <T> ObservableField<T>.toObservable(): RxObservable<T> {
    return RxObservable.create { emitter ->
        get()?.run(emitter::onNext)

        val callback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                get()?.run(emitter::onNext)
            }
        }

        addOnPropertyChangedCallback(callback)

        emitter.setDisposable(Disposables.fromAction { removeOnPropertyChangedCallback(callback) })
    }
}