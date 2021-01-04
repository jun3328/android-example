package io.github.jesterz91.finedust.common

interface BaseContract {

    interface View {
        fun showToast(msg: Any)
        fun showProgress(value: Boolean)
//        fun getContext(): Context
    }

    interface Presenter {
        fun attachView()
        fun detachView()
        fun handleError(error: Throwable)
    }
}