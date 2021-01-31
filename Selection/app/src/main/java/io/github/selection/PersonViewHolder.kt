package io.github.selection

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.selection.model.Person

class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameView = itemView.findViewById<TextView>(R.id.personName)

    private val ageView = itemView.findViewById<TextView>(R.id.personAge)

    fun bind(person: Person, isSelected: Boolean) {
        nameView.text = person.name
        ageView.text = person.age.toString()
        itemView.isActivated = isSelected
    }
}
