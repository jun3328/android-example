package io.github.jesterz91.finedust.util

open class SingletonHolder<out T, in A>(creator: (A) -> T) {

    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T = instance ?: synchronized(this) {
        instance ?: run {
            creator!!.invoke(arg).also {
                instance = it
                creator = null
            }
        }
    }
}