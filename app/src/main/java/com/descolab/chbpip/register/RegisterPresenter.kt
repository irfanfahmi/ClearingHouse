package com.descolab.chbpip.register

import android.content.Context
import android.util.Log
import com.descolab.chbpip.base.BasePresenter
import com.descolab.chbpip.service.ApiClient
import com.descolab.chbpip.service.ApiService
import com.descolab.chbpip.service.response.DataItem
import com.descolab.chbpip.service.response.DataUser
import com.descolab.chbpip.service.response.ResponseData
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class RegisterPresenter (val context: Context,
                         val mView: RegisterContract.View)
    : BasePresenter(), RegisterContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun saveregister(
        name: String,
        alias: String,
        lembaga: String,
        subLembaga: String,
        satker: String,
        noId: String,
        noHp: String,
        email: String,
        password: String,
        idInstansi: String,
        idLembaga: String,
        foto: File?
    ) {
        val params = HashMap<String, RequestBody>()
        params["name"] = RequestBody.create(MediaType.parse("text/plain"), name)
        params["alias"] = RequestBody.create(MediaType.parse("text/plain"), alias)
        params["lembaga"] = RequestBody.create(MediaType.parse("text/plain"), lembaga)
        params["instansi"] = RequestBody.create(MediaType.parse("text/plain"), subLembaga)
        params["unit_satker"] = RequestBody.create(MediaType.parse("text/plain"), satker)
        params["no_id"] = RequestBody.create(MediaType.parse("text/plain"), noId)
        params["no_hp"] = RequestBody.create(MediaType.parse("text/plain"), noHp)
        params["email"] = RequestBody.create(MediaType.parse("text/plain"), email)
        params["password"] = RequestBody.create(MediaType.parse("text/plain"), password)
        params["idInstansi"] = RequestBody.create(MediaType.parse("text/plain"), idInstansi)
        params["idLembaga"] = RequestBody.create(MediaType.parse("text/plain"), idLembaga)

        var call: Call<ResponseData<DataUser>>? = null
        Log.d("Register", foto.toString())
        val pathFolder0 = RequestBody.create(MediaType.parse("avatar/*"), foto)
        val gambar0 = MultipartBody.Part.createFormData("foto", name, pathFolder0)
        call = apiService.postRegister(params,gambar0)
        Log.d("Register", gambar0.toString())

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

    override fun loadLembaga() {
        val call = apiService.getLembaga()
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseData<ArrayList<DataItem>>> {
            override fun onResponse(call: Call<ResponseData<ArrayList<DataItem>>>, response: Response<ResponseData<ArrayList<DataItem>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (resource != null) {
                        resource.getData()?.let { mView.showLembaga(it) }
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

    override fun loadSubLembaga(id: String) {
        val params = HashMap<String, String>()
        params["id_lembaga"] = id
        val call = apiService.getSubLembaga(params)
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseData<ArrayList<DataItem>>> {
            override fun onResponse(call: Call<ResponseData<ArrayList<DataItem>>>, response: Response<ResponseData<ArrayList<DataItem>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (resource != null) {
                        resource.getData()?.let { mView.showSubLembaga(it) }
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