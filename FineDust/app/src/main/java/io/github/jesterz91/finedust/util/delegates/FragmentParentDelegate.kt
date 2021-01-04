package io.github.jesterz91.finedust.util.delegates

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T> fragmentParentDelegate(fragment: Fragment) = object : ReadOnlyProperty<Fragment, T?> {

    private var value: T? = null

    init {
        fragment.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onFragmentCreated() {
                value = fragment.requireActivity() as? T
                Log.e("parentActivityDelegate", "Fragment ON_CREATE value $value")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onFragmentDestroyed() {
                value = null
                Log.e("parentActivityDelegate", "Fragment ON_DESTROY value $value")
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T? = value
}