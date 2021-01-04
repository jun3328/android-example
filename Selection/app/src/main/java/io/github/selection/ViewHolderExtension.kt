package io.github.selection

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

val RecyclerView.ViewHolder.itemDetails
    get() = object : ItemDetailsLookup.ItemDetails<Long>() {
        override fun getSelectionKey(): Long? = itemId

        override fun getPosition(): Int = adapterPosition

        override fun inSelectionHotspot(e: MotionEvent) = true
    }