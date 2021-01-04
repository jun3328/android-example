package io.github.jesterz91.endmenudrawer

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import kotlin.math.max
import kotlin.math.min

class EndMenuDrawerToggle(
    toolbar: Toolbar,
    private val drawerLayout: DrawerLayout,
    private val openDrawerContentDesc: Int,
    private val closeDrawerContentDesc: Int
) : DrawerLayout.DrawerListener, MenuItem.OnMenuItemClickListener {

    private val arrowDrawable: DrawerArrowDrawable = DrawerArrowDrawable(toolbar.context)
    private lateinit var toggleItem: MenuItem

    init {
        arrowDrawable.direction = DrawerArrowDrawable.ARROW_DIRECTION_END
    }

    fun setToggleOnMenu(menu: Menu) {
        toggleItem = menu.add(openDrawerContentDesc)

        toggleItem.apply {
            icon = arrowDrawable
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            setOnMenuItemClickListener(this@EndMenuDrawerToggle)
            setPosition(if (drawerLayout.isDrawerOpen(GravityCompat.END)) 1f else 0f)
        }
    }

    override fun onMenuItemClick(menuItem: MenuItem?): Boolean {
        val drawerLockMode = drawerLayout.getDrawerLockMode(GravityCompat.END)
        if (drawerLayout.isDrawerVisible(GravityCompat.END) && drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_OPEN) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else if (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            drawerLayout.openDrawer(GravityCompat.END)
        }
        return true
    }

    private fun setPosition(position: Float) {
        if (position == 1f) {
            arrowDrawable.setVerticalMirror(true)
            toggleItem.setTitle(closeDrawerContentDesc)
        } else if (position == 0f) {
            arrowDrawable.setVerticalMirror(false)
            toggleItem.setTitle(openDrawerContentDesc)
        }
        arrowDrawable.progress = position
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) = setPosition(min(1f, max(0f, slideOffset)))

    override fun onDrawerOpened(drawerView: View) = setPosition(1f)

    override fun onDrawerClosed(drawerView: View) = setPosition(0f)

    override fun onDrawerStateChanged(newState: Int) {}
}
