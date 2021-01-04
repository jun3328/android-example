package io.github.jesterz91.finedust.util.extension

import android.view.View
import android.widget.ProgressBar

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toInvisible() {
    visibility = View.INVISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

fun ProgressBar.show(value: Boolean) {
    if (value) {
        toVisible()
    } else {
        toGone()
    }
}