package io.github.selection.tracker

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import io.github.selection.PersonViewHolder
import io.github.selection.model.Person

class PersonDetailsLookup(
    private val recyclerView: RecyclerView,
    private val selectionAdapter: SelectionAdapter<Person>
) : ItemDetailsLookup<Person>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<Person>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        return (view?.run(recyclerView::getChildViewHolder) as? PersonViewHolder)?.adapterPosition?.run(selectionAdapter::getSelectionDetails)
    }
}