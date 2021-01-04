package io.github.jesterz91.finedust.ui

import io.github.jesterz91.finedust.common.BaseContract

interface MainContract {

    interface View: BaseContract.View {
//        fun showDialog()
    }

    interface Presenter: BaseContract.Presenter {
        fun requestPermissions()
    }
}