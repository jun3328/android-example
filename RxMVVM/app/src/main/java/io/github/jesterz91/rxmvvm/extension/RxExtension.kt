package io.github.jesterz91.rxmvvm.extension

import io.github.jesterz91.rxmvvm.util.RxField
import io.reactivex.Observable

fun <T> Observable<T>.toRxField() = RxField(this)

fun <T> Observable<T>.toRxField(defaultValue: T) = RxField(this, defaultValue)