package io.github.jesterz91.viewpagerindicator.book

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tv1 = itemView.findViewById<TextView>(android.R.id.text1)

    private val tv2 = itemView.findViewById<TextView>(android.R.id.text2)

    fun bind(book: Book) {
        tv1.text = book.title
        tv2.text = book.subTitle
    }
}