package io.github.jesterz91.testapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import io.github.jesterz91.testapp.common.BaseViewModel
import io.github.jesterz91.testapp.data.*
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class GitHubViewModel(private val gitHubApi: GitHubApi) : BaseViewModel() {

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val userInfo = MutableLiveData<UserInfo>()
    private val userRepo = MutableLiveData<List<UserRepo>>()

    private val _gitHubUser = MediatorLiveData<List<GitHubUser>>().apply {

        addSource(userInfo) { info ->
            val repos = userRepo.value

            value = if (repos != null) {
                listOf(info, *(repos.toTypedArray()))
            } else {
                listOf(info)
            }
        }

        addSource(userRepo) { repos ->
            val info = userInfo.value

            value = if (info != null) {
                listOf(info, *(repos.toTypedArray()))
            } else {
                repos
            }
        }
    }

    val gitHubUser: LiveData<List<GitHubUser>>
        get() = _gitHubUser

    fun getUserInfo(user: User) {
        gitHubApi.getUserInfo(user)
            .doOnSubscribe { _isLoading.postValue(true) }
            .doAfterTerminate { _isLoading.postValue(false) }
            .doOnSuccess(userInfo::postValue)
            .flatMap { gitHubApi.getUserRepos(user) }
            .map { repos -> repos.sortedByDescending { it.star } }
            .subscribeOn(Schedulers.io())
            .subscribe(userRepo::postValue)
            .addTo(disposables)
    }
}