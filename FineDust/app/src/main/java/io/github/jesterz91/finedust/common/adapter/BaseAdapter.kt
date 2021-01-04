package io.github.jesterz91.finedust.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ITEM, VH : BaseViewHolder<ITEM>>(protected val items: MutableList<ITEM>) : RecyclerView.Adapter<VH>(),
    BaseAdapterContract.AdapterView, BaseAdapterContract.AdapterModel<ITEM> {

    abstract val layoutResId: Int

    abstract fun createViewHolder(itemView: View): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return createViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bindItem(items[position])

    override fun refresh() = notifyDataSetChanged()

    override fun getCount(): Int = itemCount

    override fun getItem(position: Int): ITEM = items[position]

    override fun addItem(item: ITEM) {
        items.add(item)
    }

    override fun removeItem(item: ITEM) {
        items.remove(item)
    }

    override fun clear() = items.clear()
}