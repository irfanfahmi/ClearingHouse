package com.descolab.chbpip.login

import android.content.Context
import android.util.Log
import com.descolab.chbpip.base.BasePresenter
import com.descolab.chbpip.service.ApiClient
import com.descolab.chbpip.service.ApiService
import com.descolab.chbpip.service.response.DataUser
import com.descolab.chbpip.service.response.ResponseData
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class LoginPresenter(val context: Context,
                     val mView: LoginContract.View):
    BasePresenter(), LoginContract.UserActionListener {
    val apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)

    override fun login(phone: String, password: String) {
        val params = HashMap<String, RequestBody>()
        params["noHp"] = RequestBody.create(MediaType.parse("text/plain"), phone)
        params["password"] = RequestBody.create(MediaType.parse("text/plain"), password)
        mView.showProgressDialog(true)
        val call = apiService.postLogin(params)
        call.enqueue(object : Callback<ResponseData<DataUser>> {
            override fun onResponse(call: Call<ResponseData<DataUser>>, response: Response<ResponseData<DataUser>>) {
                mView.showProgressDialog(false)
                val resource = response.body()
                if (response.isSuccessful) {
                    if (response.code() == 200 && resource?.getCode() == 200) {
                        resource.getData()?.let { mView.enterMainActivity(it) }
                    } else if (resource?.getCode() == 400) {
                        showDialog(context, "email atau password salah!")
                    }
                } else if (resource?.getCode() == 400) {
                    showDialog(context, "email atau password salah!")
                } else if (resource?.getCode() == 500) {
                    showDialog(context, "Terjadi kesalahan pada server")
                } else {
                    Log.e("Error Code", response.code().toString())
                }
            }


            override fun onFailure(call: Call<ResponseData<DataUser>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
                Log.e("onFailure", t.message.toString())
            }
        })
    }


}