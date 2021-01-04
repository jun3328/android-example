package io.github.jesterz91.rxmvvm.main

import androidx.databinding.ObservableField
import io.github.jesterz91.rxmvvm.api.GithubService
import io.github.jesterz91.rxmvvm.api.UserResponse
import io.github.jesterz91.rxmvvm.common.BaseViewModel
import io.github.jesterz91.rxmvvm.extension.toRxField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel : BaseViewModel() {

    private val userSubject = PublishSubject.create<String>()
    val user = userSubject.toRxField()
    val query = ObservableField<String>()

    fun search(query: String) {
        GithubService.api.getUserInfo(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(UserResponse::toString)
            .subscribe(userSubject::onNext, Throwable::printStackTrace)
            .addTo(disposables)
    }
}