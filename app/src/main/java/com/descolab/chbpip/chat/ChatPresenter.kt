package com.descolab.chbpip.chat

import android.content.Context
import com.descolab.chbpip.base.BasePresenter
import com.descolab.chbpip.service.ApiClient
import com.descolab.chbpip.service.ApiService
import com.descolab.chbpip.service.response.Chat
import com.descolab.chbpip.service.response.DataItem
import com.descolab.chbpip.service.response.ResponseChat
import com.descolab.chbpip.service.response.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatPresenter (val context: Context,
                     val mView: ChatContract.View)
    : BasePresenter(), ChatContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun loadHistory(idPengguna: String) {
    }

    override fun cekPercakapan(idPengguna: String,idDirektorat: String) {
        val params = HashMap<String, String>()
        params["id_pengguna"] = idPengguna
        params["id_admin"] = idDirektorat.toInt().plus(1).toString()
        val call = apiService.getPercakapan(params)
//        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseData<ArrayList<DataItem>>> {
            override fun onResponse(call: Call<ResponseData<ArrayList<DataItem>>>, response: Response<ResponseData<ArrayList<DataItem>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (resource != null) {
                        resource.getData()?.let { mView.showCekPercakapan(it) }
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
    override fun cekUpdatePercakapan(idPengguna: String,idDirektorat: String) {
        val params = HashMap<String, String>()
        params["id_pengguna"] = idPengguna
        params["id_admin"] = idDirektorat.toInt().plus(1).toString()
        val call = apiService.getPercakapan(params)
//        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseData<ArrayList<DataItem>>> {
            override fun onResponse(call: Call<ResponseData<ArrayList<DataItem>>>, response: Response<ResponseData<ArrayList<DataItem>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (resource != null) {
                        resource.getData()?.let { mView.showCekPercakapan(it) }
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

    override fun postPercakapan(idPengguna: String, message: String, idDirektorat: String, tipePengajuan: String) {
        var call: Call<ResponseChat<Chat>>? = null
        call = apiService.postPercakapan(idPengguna,message,idDirektorat.toInt().plus(1).toString(),tipePengajuan)
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseChat<Chat>> {
            override fun onResponse(call: Call<ResponseChat<Chat>>, response: Response<ResponseChat<Chat>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (resource?.message.equals("success")) {
                        mView.showPercakapan()
                    }else{
                        mView.showPercakapan()
                    }

                } else {
                    showDialog(context, "Error")
                }
            }

            override fun onFailure(call: Call<ResponseChat<Chat>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
            }


        })

    }

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