package io.github.jesterz91.testapp.util

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.show(loading: Boolean) {
    visibility = if (loading) {
        View.VISIBLE
    } else {
        View.GONE
    }
}