package io.github.selection

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.selection.model.Person

class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameView = itemView.findViewById<TextView>(R.id.personName)

    private val ageView = itemView.findViewById<TextView>(R.id.personAge)

    fun bind(person: Person) {
        nameView.text = person.name
        ageView.text = person.age.toString()
    }

    fun setState(isSelected: Boolean) {
        if (isSelected) {
            itemView.setBackgroundResource(R.color.colorPrimary)
        } else {
            itemView.setBackgroundResource(android.R.color.white)
        }
    }
}
