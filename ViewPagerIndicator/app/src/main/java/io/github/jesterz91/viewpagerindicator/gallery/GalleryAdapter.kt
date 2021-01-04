package io.github.jesterz91.viewpagerindicator.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.jesterz91.viewpagerindicator.R

class GalleryAdapter : RecyclerView.Adapter<GalleryViewHolder>() {

    private val item = arrayOf(
        "https://picsum.photos/id/201/200/300",
        "https://picsum.photos/id/123/200/300",
        "https://picsum.photos/id/221/200/300",
        "https://picsum.photos/id/133/200/300",
        "https://picsum.photos/id/111/200/300"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) = holder.bind(item[position])

    override fun getItemCount(): Int = item.size
}
