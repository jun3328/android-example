package io.github.selection.tracker

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import io.github.selection.model.Person

data class PersonDetails(
    private val position: Int,
    private val Person: Person?
) : ItemDetailsLookup.ItemDetails<Person>() {

    override fun getPosition(): Int = position

    override fun getSelectionKey(): Person? = Person

    override fun inSelectionHotspot(e: MotionEvent) = true
}