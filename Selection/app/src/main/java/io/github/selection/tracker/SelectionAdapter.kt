package io.github.selection.tracker

import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker

interface SelectionAdapter<T> {

    val tracker: SelectionTracker<T>?

    fun getSelectionKey(position: Int): T?

    fun getSelectionPosition(item: T): Int

    fun getSelectionDetails(position: Int): ItemDetailsLookup.ItemDetails<T>
}