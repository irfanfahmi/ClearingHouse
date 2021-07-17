package com.descolab.chbpip.home

import android.content.Context
import com.descolab.chbpip.base.BasePresenter
import com.descolab.chbpip.service.ApiClient
import com.descolab.chbpip.service.ApiService

class HomePresenter (val context: Context,
                     val mView: HomeContract.View)
    : BasePresenter(), HomeContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun loadTopHeadline() {
    }


}