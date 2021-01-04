package io.github.jesterz91.finedust.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import io.github.jesterz91.finedust.R
import io.github.jesterz91.finedust.common.BaseActivity
import io.github.jesterz91.finedust.ui.tools.ToolsFragmentListener
import io.github.jesterz91.finedust.util.Constant
import io.github.jesterz91.finedust.util.PermissionManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View, ToolsFragmentListener {

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_weather, R.id.nav_tools),
            drawer_layout
        )
    }

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    private val permissionManager by lazy { PermissionManager(this) }

    override val layoutResId: Int = R.layout.activity_main

    override val presenter: MainContract.Presenter by lazy { MainPresenter(this, permissionManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)

        presenter.requestPermissions()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constant.REQUEST_CODE_PERMISSION -> {
                if (permissionManager.checkAllPermission()) {
                    Log.e(TAG, "Permission Granted")
                } else {
                    Log.e(TAG, "Permission Not Granted")
                }
            }
        }
    }

    override fun onToolFragment() {
        Log.e(TAG, "뜨앗 아잇")
        toast("툴즈에서 눌렀다")
    }
}
