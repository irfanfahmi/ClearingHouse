package com.descolab.chbpip.inbox

import android.content.Context
import com.descolab.chbpip.base.BasePresenter
import com.descolab.chbpip.service.ApiClient
import com.descolab.chbpip.service.ApiService
import com.descolab.chbpip.service.response.DataItem
import com.descolab.chbpip.service.response.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InboxPresenter(val context: Context,
                     val mView: InboxContract.View)
    : BasePresenter(), InboxContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun loadInbox(id: String) {
        val params = HashMap<String, String>()
        params["id_pengguna"] = id
        val call = apiService.getInbox(params)
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseData<ArrayList<DataItem>>> {
            override fun onResponse(call: Call<ResponseData<ArrayList<DataItem>>>, response: Response<ResponseData<ArrayList<DataItem>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (resource != null) {
                        resource.getData()?.let { mView.showInbox(it) }
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