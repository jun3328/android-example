package io.github.selection

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionTracker
import io.github.selection.model.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val personAdapter = PersonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = personAdapter.apply {
                submitList((1..100).map { Person("person $it", it) })
            }
        }

        personAdapter.tracker?.run {
            addObserver(object : SelectionTracker.SelectionObserver<Person>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    Log.d("Selection", "======SELECTED_ITEMS======")
                    selection.forEach { person ->
                        Log.i("Selection", "$person selected")
                    }
                    Log.d("Selection", "==========================")
                }
            })
        }
    }
}

//val RecyclerView.ViewHolder.itemDetails
//    get() = object : ItemDetailsLookup.ItemDetails<Long>() {
//        override fun getSelectionKey(): Long? = itemId
//
//        override fun getPosition(): Int = adapterPosition
//
//        override fun inSelectionHotspot(e: MotionEvent) = true
//    }