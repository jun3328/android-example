package io.github.selection

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.selection.model.Person

class PersonAdapter : ListAdapter<Person, PersonViewHolder>(diff_callback) {

    private var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        tracker = SelectionTracker.Builder(
            "selection_id",
            recyclerView,
//            StableIdKeyProvider(recyclerView)
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
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build().apply {

            addObserver(object : SelectionTracker.SelectionObserver<Long>() {

                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    selection.map { getItem(it.toInt()) }.run(::println)
                }
            })
        }
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
        holder.bind(getItem(position))
        holder.setState(tracker?.isSelected(position.toLong()) ?: false)
    }

    override fun getItemId(position: Int): Long = position.toLong()

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