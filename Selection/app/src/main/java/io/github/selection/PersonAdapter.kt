package io.github.selection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.selection.model.Person
import io.github.selection.tracker.PersonDetails
import io.github.selection.tracker.PersonDetailsLookup
import io.github.selection.tracker.PersonKeyProvider
import io.github.selection.tracker.SelectionAdapter

class PersonAdapter : ListAdapter<Person, PersonViewHolder>(diff_callback), SelectionAdapter<Person> {

    override var tracker: SelectionTracker<Person>? = null
        private set

    private val selectionId = "selection_id"

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        tracker = SelectionTracker.Builder(
            selectionId,
            recyclerView,
            PersonKeyProvider(this),
            PersonDetailsLookup(recyclerView, this),
            StorageStrategy.createParcelableStorage(Person::class.java)
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        tracker = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position), tracker?.isSelected(getItem(position)) ?: false)
    }

    override fun getSelectionKey(position: Int): Person? = currentList[position]

    override fun getSelectionPosition(item: Person): Int = currentList.indexOf(item)

    override fun getSelectionDetails(position: Int): ItemDetailsLookup.ItemDetails<Person> = PersonDetails(position, getItem(position))

    companion object {
        val diff_callback = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem == newItem
            }
        }
    }
}
/*
    init { setHasStableIds(true) }

    override fun getItemId(position: Int): Long = position.toLong()

    tracker = SelectionTracker.Builder(
        "selection_id",
        recyclerView,
        StableIdKeyProvider(recyclerView)
        object : ItemKeyProvider<Long>(SCOPE_MAPPED) {
            override fun getKey(position: Int): Long? = getItemId(position)

            override fun getPosition(key: Long): Int = recyclerView.findViewHolderForItemId(key).layoutPosition
        },
        object : ItemDetailsLookup<Long>() {
            override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
                val view: View? = recyclerView.findChildViewUnder(e.x, e.y)
                return view?.let(recyclerView::getChildViewHolder)?.itemDetails
            }
        },
        StorageStrategy.createLongStorage()
    ).withSelectionPredicate(SelectionPredicates.createSelectAnything())
     .build()
*/
