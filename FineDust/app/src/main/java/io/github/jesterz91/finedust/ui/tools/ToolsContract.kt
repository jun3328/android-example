package io.github.jesterz91.finedust.ui.tools

import io.github.jesterz91.finedust.common.BaseContract

interface ToolsContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter {
        fun call()
    }
}