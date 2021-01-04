package io.github.jesterz91.finedust.common

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import io.github.jesterz91.finedust.util.delegates.Tags
import io.github.jesterz91.finedust.util.extension.show
import io.github.jesterz91.finedust.util.extension.toGone
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<P : BaseContract.Presenter> : AppCompatActivity(), BaseContract.View {

    abstract val presenter: P

    abstract val layoutResId: Int

    protected val TAG by Tags()

    protected val viewDisposables by lazy { CompositeDisposable() }

    private val progressBar by lazy { ProgressBar(this).apply { toGone() } }

    protected val sharedPreferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    private val params by lazy {
        FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        addContentView(progressBar, params)
        presenter.attachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        viewDisposables.clear()
    }

    override fun showToast(msg: Any) = Toast.makeText(this, "$msg", Toast.LENGTH_SHORT).show()

    override fun showProgress(value: Boolean) = progressBar.show(value)
}