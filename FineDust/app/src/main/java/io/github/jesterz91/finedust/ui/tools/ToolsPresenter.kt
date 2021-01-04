package io.github.jesterz91.finedust.ui.tools

import android.util.Log
import io.github.jesterz91.finedust.common.BasePresenter

class ToolsPresenter(
    view: ToolsContract.View,
    private val toolsFragmentListener: ToolsFragmentListener?
) : BasePresenter<ToolsContract.View>(view), ToolsContract.Presenter {

    override fun call() {
        toolsFragmentListener?.onToolFragment() ?: run {
            Log.e("ToolsFragment", "parent activity not implement ToolsFragmentListener")
        }
    }
}