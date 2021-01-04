package io.github.jesterz91.finedust.common.adapter

interface BaseAdapterContract {

    interface AdapterView {
        fun refresh()
    }

    interface AdapterModel<ITEM> {
        fun getCount(): Int
        fun getItem(position: Int): ITEM
        fun addItem(item: ITEM)
        fun removeItem(item: ITEM)
        fun clear()
    }
}