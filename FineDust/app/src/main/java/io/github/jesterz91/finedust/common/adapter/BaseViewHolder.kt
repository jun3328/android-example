package io.github.jesterz91.finedust.common.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<M>(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val requestManager by lazy { Glide.with(itemView.context) }

    abstract fun bindItem(item: M)
}