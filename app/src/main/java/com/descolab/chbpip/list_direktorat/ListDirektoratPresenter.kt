package com.descolab.chbpip.list_direktorat

import android.content.Context
import com.descolab.chbpip.base.BasePresenter
import com.descolab.chbpip.service.ApiClient
import com.descolab.chbpip.service.ApiService
import com.descolab.chbpip.service.response.DataItem
import com.descolab.chbpip.service.response.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListDirektoratPresenter (val context: Context,
                               val mView: ListDirektoratContract.View)
    : BasePresenter(), ListDirektoratContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun loadDirektorat() {
       val call = apiService.getListDirektorat()
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseData<ArrayList<DataItem>>> {
            override fun onResponse(call: Call<ResponseData<ArrayList<DataItem>>>, response: Response<ResponseData<ArrayList<DataItem>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (resource != null) {
                        resource.getData()?.let { mView.showListDirektorat(it) }
                    }

                } else {
                    showDialog(context, "Error")
                }
            }

            override fun onFailure(call: Call<ResponseData<ArrayList<DataItem>>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
            }


        })
    }

}