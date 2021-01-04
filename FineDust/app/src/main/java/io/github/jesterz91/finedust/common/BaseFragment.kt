package io.github.jesterz91.finedust.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.github.jesterz91.finedust.util.delegates.Tags
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<P : BaseContract.Presenter>: Fragment(), BaseContract.View {

    abstract val presenter: P

    abstract val layoutResId: Int

//    abstract var progressBar: ProgressBar

    protected val TAG by Tags()

    protected val viewDisposables by lazy { CompositeDisposable() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.attachView()
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        viewDisposables.clear()
    }

    override fun showToast(msg: Any) = Toast.makeText(requireActivity(), "$msg", Toast.LENGTH_SHORT).show()

//    override fun showProgress(value: Boolean) {
//        (requireActivity() as? BaseContract.View)?.showProgress(value)
//    }

//    override fun getContext(): Context = requireActivity()
}