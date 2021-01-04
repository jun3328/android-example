package io.github.jesterz91.finedust.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.github.jesterz91.finedust.util.delegates.Tags

class PermissionManager(private val activity: Activity) {

    private val TAG by Tags()

    private val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)

    fun requestPermission(permission: String) {
        if(!checkPermission(permission)) {
            Log.e(TAG, "requestPermission $permission")
            ActivityCompat.requestPermissions(activity, arrayOf(permission), Constant.REQUEST_CODE_PERMISSION)
        }
    }

    fun checkPermission(permission: String): Boolean {
        Log.e(TAG, "checkPermissions : $permission")
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "$permission denied")
            return false
        }
        Log.e(TAG, "$permission granted")
        return true
    }

    fun requestAllPermission() {
        if (!checkAllPermission()) {
            Log.e(TAG, "request all permission")
            ActivityCompat.requestPermissions(activity, permissions, Constant.REQUEST_CODE_PERMISSION)
        }
    }

    fun checkAllPermission(): Boolean {
        permissions.forEach {
            if (!checkPermission(it)) {
                Log.e(TAG, "some permission denied")
                return false
            }
        }
        Log.e(TAG, "all permission granted")
        return true
    }
}
