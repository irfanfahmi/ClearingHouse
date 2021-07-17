package com.descolab.chbpip.mitra_mou

import android.content.Context
import com.descolab.chbpip.base.BasePresenter
import com.descolab.chbpip.service.ApiClient
import com.descolab.chbpip.service.ApiService
import com.descolab.chbpip.service.response.DataItem
import com.descolab.chbpip.service.response.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MouPresenter (val context: Context,
                    val mView: MouContract.View)
    : BasePresenter(), MouContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun loadMou(id_user: String,idInstansi: String,idLembaga: String) {
        val params = HashMap<String, String>()
        params["id_pengguna"] = id_user
        params["idInstansi"] = idInstansi
        params["idLembaga"] = idLembaga
        val call = apiService.getMou(params)
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseData<ArrayList<DataItem>>> {
            override fun onResponse(call: Call<ResponseData<ArrayList<DataItem>>>, response: Response<ResponseData<ArrayList<DataItem>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (resource != null) {
                        resource.getData()?.let { mView.showMou(it) }
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

    override fun loadPks(id_user: String,idInstansi: String,idLembaga: String) {
        val params = HashMap<String, String>()
        params["id_pengguna"] = id_user
        params["idInstansi"] = idInstansi
        params["idLembaga"] = idLembaga
        val call = apiService.getPks(params)
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseData<ArrayList<DataItem>>> {
            override fun onResponse(call: Call<ResponseData<ArrayList<DataItem>>>, response: Response<ResponseData<ArrayList<DataItem>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (resource != null) {
                        resource.getData()?.let { mView.showMou(it) }
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

    override fun loadMonev(id_user: String,idInstansi: String,idLembaga: String) {
        val params = HashMap<String, String>()
        params["id_pengguna"] = id_user
        params["idInstansi"] = idInstansi
        params["idLembaga"] = idLembaga
        val call = apiService.getMonev(params)
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseData<ArrayList<DataItem>>> {
            override fun onResponse(call: Call<ResponseData<ArrayList<DataItem>>>, response: Response<ResponseData<ArrayList<DataItem>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (resource != null) {
                        resource.getData()?.let { mView.showMou(it) }
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