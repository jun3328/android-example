package io.github.jesterz91.finedust.ui.tools

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import io.github.jesterz91.finedust.R
import io.github.jesterz91.finedust.common.BaseFragment
import io.github.jesterz91.finedust.util.delegates.fragmentParentDelegate
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_tools.*

class ToolsFragment : BaseFragment<ToolsContract.Presenter>(), ToolsContract.View {

    private val listener: ToolsFragmentListener? by fragmentParentDelegate<ToolsFragmentListener>(this)

    override val presenter: ToolsContract.Presenter by lazy { ToolsPresenter(this, listener) }

    override val layoutResId: Int = R.layout.fragment_tools

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_tools.clicks()
            .subscribe { presenter.call() }
            .addTo(viewDisposables)
    }

    override fun showProgress(value: Boolean) { }
}