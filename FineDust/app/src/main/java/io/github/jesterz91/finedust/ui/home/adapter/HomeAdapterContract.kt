package io.github.jesterz91.finedust.ui.home.adapter

import io.github.jesterz91.finedust.common.adapter.BaseAdapterContract
import io.github.jesterz91.finedust.ui.home.model.DustInfo

interface HomeAdapterContract {

    interface AdapterView : BaseAdapterContract.AdapterView {

    }

    interface AdapterModel : BaseAdapterContract.AdapterModel<DustInfo> {
    }
}