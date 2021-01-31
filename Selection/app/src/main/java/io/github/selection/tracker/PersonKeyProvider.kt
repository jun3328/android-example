package io.github.selection.tracker

import androidx.recyclerview.selection.ItemKeyProvider
import io.github.selection.model.Person

class PersonKeyProvider(private val selectionAdapter: SelectionAdapter<Person>) : ItemKeyProvider<Person>(SCOPE_MAPPED) {

    override fun getKey(position: Int): Person? = selectionAdapter.getSelectionKey(position)

    override fun getPosition(key: Person): Int = selectionAdapter.getSelectionPosition(key)
}