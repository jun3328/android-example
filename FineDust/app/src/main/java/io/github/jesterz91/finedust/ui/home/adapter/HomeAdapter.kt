package io.github.jesterz91.finedust.ui.home.adapter

import android.view.View
import io.github.jesterz91.finedust.R
import io.github.jesterz91.finedust.common.adapter.BaseAdapter
import io.github.jesterz91.finedust.common.adapter.BaseViewHolder
import io.github.jesterz91.finedust.ui.home.model.DustInfo
import kotlinx.android.synthetic.main.item_dust.*

class HomeAdapter(items: MutableList<DustInfo>) : BaseAdapter<DustInfo, HomeAdapter.HomeViewHolder>(items),
    HomeAdapterContract.AdapterView, HomeAdapterContract.AdapterModel {

    override val layoutResId: Int = R.layout.item_dust

    override fun createViewHolder(itemView: View): HomeViewHolder = HomeViewHolder(itemView)

    inner class HomeViewHolder(itemView: View) : BaseViewHolder<DustInfo>(itemView) {

        override fun bindItem(item: DustInfo) {
            locationTextView.text = item.station
            timeTextView.text = item.time
            dustTextView.text = "${item.grade} , ${item.value}"
        }
    }
}