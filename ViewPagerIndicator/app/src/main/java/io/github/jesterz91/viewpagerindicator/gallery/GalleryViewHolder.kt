package io.github.jesterz91.viewpagerindicator.gallery

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView = itemView.imgView

    fun bind(img: String) {

        Glide.with(imageView)
            .load(img)
            .into(imageView)
    }
}