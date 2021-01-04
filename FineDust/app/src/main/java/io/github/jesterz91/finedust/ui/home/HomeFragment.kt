package io.github.jesterz91.finedust.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.location.LocationServices
import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.rxbinding3.view.clicks
import io.github.jesterz91.finedust.R
import io.github.jesterz91.finedust.common.BaseFragment
import io.github.jesterz91.finedust.common.dialog.AddLocationDialog
import io.github.jesterz91.finedust.database.LocationDatabase
import io.github.jesterz91.finedust.ui.home.adapter.HomeAdapter
import io.github.jesterz91.finedust.util.extension.show
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeContract.Presenter>(), HomeContract.View {

    var cnt = 1

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private val locationDao by lazy {
        LocationDatabase.getInstance(requireActivity()).locationDao()
    }

    private val homeAdapter by lazy {
        HomeAdapter(
            arrayListOf()
        )
    }

    override val presenter: HomeContract.Presenter by lazy {
        HomePresenter(this, homeAdapter, homeAdapter, locationDao, fusedLocationProviderClient)
    }

    override val layoutResId: Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TabLayout , ViewPager2 연결
        TabLayoutMediator(tabLayout, viewPager2.apply { adapter = homeAdapter }) { tab, position ->
            tab.text = presenter.getItem(position).location
            viewPager2.setCurrentItem(0, true)
        }.attach()

        setViewEvent()

        presenter.requestLastLocation()
    }

    override fun lastLocationFetched() {
        Log.i(TAG, "lastLocationFetched")
        presenter.fetchSavedLocation()
    }

    private fun setViewEvent() {
        fab.clicks()
            .subscribe { showAddLocationDialog() }
            .addTo(viewDisposables)
    }

    private fun showAddLocationDialog() {
        AddLocationDialog.newInstance(param1 = cnt++, param2 = "냐하하하하") { city ->
//            GeoUtil.getLocationFromName(requireActivity(), city,
//                onSuccess = { lat, lon -> presenter.saveLocation(city, lat, lon) },
//                onError = { msg -> showToast(msg) }
//            )
        }.show(parentFragmentManager, TAG)
    }

    override fun showProgress(value: Boolean) = progressBar.show(value)
}