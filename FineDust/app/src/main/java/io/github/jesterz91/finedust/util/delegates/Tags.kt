package io.github.jesterz91.finedust.util.delegates

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Tags : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return thisRef::class.java.simpleName
    }
}