package io.github.jesterz91.finedust.util.delegates

import android.os.Bundle
import androidx.fragment.app.Fragment
import io.github.jesterz91.finedust.util.extension.put
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T> fragmentArgumentDelegate() = object : ReadWriteProperty<Fragment, T> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val key = property.name
        return thisRef.arguments?.get(key) as? T
            ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val key = property.name
        val args = thisRef.arguments ?: Bundle().also(thisRef::setArguments)
        args.put(key, value)
    }
}