package io.github.jesterz91.finedust.common

import android.util.Log
import io.github.jesterz91.finedust.util.delegates.Tags
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<V: BaseContract.View>(protected val view: V): BaseContract.Presenter, CoroutineScope {

    protected val TAG by Tags()

    protected val disposables by lazy { CompositeDisposable() }

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun attachView() {
        Log.i(TAG, "View Attached")
    }

    override fun detachView() {
        Log.i(TAG, "View Detached")
        disposables.clear()
        job.cancelChildren()
    }

    override fun handleError(error: Throwable) {
        Log.e(TAG, "${error.message}")
        error.printStackTrace()
    }
}