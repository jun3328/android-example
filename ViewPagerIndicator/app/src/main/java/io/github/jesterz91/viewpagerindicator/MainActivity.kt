package io.github.jesterz91.viewpagerindicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.jesterz91.viewpagerindicator.book.Book
import io.github.jesterz91.viewpagerindicator.book.BookAdapter
import io.github.jesterz91.viewpagerindicator.gallery.GalleryAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val bookAdapter by lazy { BookAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setContentView(R.layout.activity_main)

        viewPager2.adapter = GalleryAdapter()

        TabLayoutMediator(tabLayout, viewPager2) { tab: TabLayout.Tab, position: Int ->
            viewPager2.currentItem = 0
        }.attach()

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = bookAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL).apply {
                getDrawable(R.drawable.item_divider)?.run(::setDrawable)
            })
        }

        (1L..300L).map { Book(it, "title$it", "subtitle$it") }.run(bookAdapter::submitList)
    }
}