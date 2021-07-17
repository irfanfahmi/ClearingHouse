package com.descolab.chbpip.edit_profile

import android.content.Context
import android.util.Log
import com.descolab.chbpip.base.BasePresenter
import com.descolab.chbpip.service.ApiClient
import com.descolab.chbpip.service.ApiService
import com.descolab.chbpip.service.response.DataUser
import com.descolab.chbpip.service.response.ResponseData
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class EditProfilPresenter (val context: Context,
                           val mView: EditProfilContract.View)
    : BasePresenter(), EditProfilContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun saveUpdate(
        id: String,
        name: String,
        alias: String,
        lembaga: String,
        subLembaga: String,
        satker: String,
        noId: String,
        noHp: String,
        email: String,
        password: String,
        foto: File?
    ) {
        val params = HashMap<String, RequestBody>()
        params["id"] = RequestBody.create(MediaType.parse("text/plain"), id)
        params["name"] = RequestBody.create(MediaType.parse("text/plain"), name)
        params["alias"] = RequestBody.create(MediaType.parse("text/plain"), alias)
        params["lembaga"] = RequestBody.create(MediaType.parse("text/plain"), subLembaga)
        params["instansi"] = RequestBody.create(MediaType.parse("text/plain"), lembaga)
        params["unit_satker"] = RequestBody.create(MediaType.parse("text/plain"), satker)
        params["no_id"] = RequestBody.create(MediaType.parse("text/plain"), noId)
        params["no_hp"] = RequestBody.create(MediaType.parse("text/plain"), noHp)
        params["email"] = RequestBody.create(MediaType.parse("text/plain"), email)
        params["password"] = RequestBody.create(MediaType.parse("text/plain"), password)

        var call: Call<ResponseData<DataUser>>? = null
        Log.d("Register", foto.toString())

        if (foto.toString()!=="null"){
            val pathFolder0 = RequestBody.create(MediaType.parse("avatar/*"), foto)
            val gambar0 = MultipartBody.Part.createFormData("foto", name, pathFolder0)
            call = apiService.postUpdateUser(params,gambar0)
        }else{
            call = apiService.postUpdateUser(params)
        }

        mView.showProgressDialog(true)
        call?.enqueue(object : Callback<ResponseData<DataUser>> {
            override fun onResponse(call: Call<ResponseData<DataUser>>, response: Response<ResponseData<DataUser>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (response.code() == 200) {
                        if (resource != null){
                            resource.getData()?.let { mView.nextStep(it) }
                        }
                    } else {
                        showDialog(context, "Gagal di tambahkan!")
                    }
                } else if (response.code() == 500) {
                    showDialog(context, "Terjadi kesalahan pada server")
                } else {
                    Log.e("Error Code", response.code().toString())
                    Log.e("Error Body", response.errorBody()!!.toString())
                }
            }
            //todo data belum masuk
            override fun onFailure(call: Call<ResponseData<DataUser>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
                Log.e("onFailure", t.message.toString())
            }
        })
    }
}