package io.github.jesterz91.finedust.ui

import io.github.jesterz91.finedust.common.BasePresenter
import io.github.jesterz91.finedust.util.PermissionManager

class MainPresenter(
    view: MainContract.View,
    private val permissionManager: PermissionManager
) : BasePresenter<MainContract.View>(view), MainContract.Presenter {

    override fun requestPermissions() = permissionManager.requestAllPermission()
}