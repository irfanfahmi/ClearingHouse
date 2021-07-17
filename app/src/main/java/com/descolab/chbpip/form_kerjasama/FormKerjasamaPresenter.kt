package com.descolab.chbpip.form_kerjasama

import android.content.Context
import android.util.Log
import com.descolab.chbpip.base.BasePresenter
import com.descolab.chbpip.service.ApiClient
import com.descolab.chbpip.service.ApiService
import com.descolab.chbpip.service.response.DataItem
import com.descolab.chbpip.service.response.ResponseData
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormKerjasamaPresenter (val context: Context,
                              val mView: FormKerjasamaContract.View)
    : BasePresenter(), FormKerjasamaContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun submitPengajuan(
            perihal: String,
            tanggal: String,
            asal_pengusul: String,
            nama: String,
            unit_satker: String,
            kerjasama: String,
            pesan: String,
            status: String,
            tipe_pengajuan: String,
            id_direktorat_1: String?,
            id_direktorat_2: String?,
            id_direktorat_3: String?,
            id_direktorat_4: String?,
            id_direktorat_5: String?,
            id_user: String) {
        val params = HashMap<String, RequestBody>()
        params["perihal"] = RequestBody.create(MediaType.parse("text/plain"), perihal)
        params["tanggal"] = RequestBody.create(MediaType.parse("text/plain"), tanggal)
        params["asal_pengusul"] = RequestBody.create(MediaType.parse("text/plain"), asal_pengusul)
        params["nama"] = RequestBody.create(MediaType.parse("text/plain"), nama)
        params["unit_satker"] = RequestBody.create(MediaType.parse("text/plain"), unit_satker)
        params["kerjasama"] = RequestBody.create(MediaType.parse("text/plain"), kerjasama)
        params["pesan"] = RequestBody.create(MediaType.parse("text/plain"), pesan)
        params["status"] = RequestBody.create(MediaType.parse("text/plain"), status.toString())
        params["id_user"] = RequestBody.create(MediaType.parse("text/plain"), id_user)
        params["tipe_pengajuan"] = RequestBody.create(MediaType.parse("text/plain"), tipe_pengajuan)
        params["id_direktorat_1"] = RequestBody.create(MediaType.parse("text/plain"), id_direktorat_1)
        params["id_direktorat_2"] = RequestBody.create(MediaType.parse("text/plain"), id_direktorat_2)
        params["id_direktorat_3"] = RequestBody.create(MediaType.parse("text/plain"), id_direktorat_3)
        params["id_direktorat_4"] = RequestBody.create(MediaType.parse("text/plain"), id_direktorat_4)
        params["id_direktorat_5"] = RequestBody.create(MediaType.parse("text/plain"), id_direktorat_5)
        var call: Call<ResponseData<DataItem>>? = null
        call = apiService.postPengajuanKerjasama(params)
        mView.showProgressDialog(true)
        call?.enqueue(object : Callback<ResponseData<DataItem>> {
            override fun onResponse(call: Call<ResponseData<DataItem>>, response: Response<ResponseData<DataItem>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (response.code() == 200) {
                       mView.backToProfile()
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

            override fun onFailure(call: Call<ResponseData<DataItem>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
                t.message?.let { Log.e("onFailure", it) }
            }
        })


    }
    override fun submitPengajuanLuring(
        perihal: String,
        tanggal: String,
        jam: String,
        asal_pengusul: String,
        nama: String,
        unit_satker: String,
        tema: String,
        jenisPertemuan: String,
        status: String,
        tipe_pengajuan: String,
        idDirektorat: String?,
        id_user: String) {
        val params = HashMap<String, RequestBody>()
        params["perihal"] = RequestBody.create(MediaType.parse("text/plain"), perihal)
        params["waktu"] = RequestBody.create(MediaType.parse("text/plain"), jam)
        params["tanggal"] = RequestBody.create(MediaType.parse("text/plain"), tanggal)
        params["asal_pengusul"] = RequestBody.create(MediaType.parse("text/plain"), asal_pengusul)
        params["nama"] = RequestBody.create(MediaType.parse("text/plain"), nama)
        params["unit_satker"] = RequestBody.create(MediaType.parse("text/plain"), unit_satker)
        params["tema"] = RequestBody.create(MediaType.parse("text/plain"), tema)
        params["jenisPertemuan"] = RequestBody.create(MediaType.parse("text/plain"), jenisPertemuan)
        params["status"] = RequestBody.create(MediaType.parse("text/plain"), status.toString())
        params["id_user"] = RequestBody.create(MediaType.parse("text/plain"), id_user)
        params["tipe_pengajuan"] = RequestBody.create(MediaType.parse("text/plain"), tipe_pengajuan)
        params["idDirektorat"] = RequestBody.create(MediaType.parse("text/plain"), idDirektorat)
        var call: Call<ResponseData<DataItem>>? = null
        call = apiService.postPengajuanPertemuan(params)
        mView.showProgressDialog(true)
        call?.enqueue(object : Callback<ResponseData<DataItem>> {
            override fun onResponse(call: Call<ResponseData<DataItem>>, response: Response<ResponseData<DataItem>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (response.code() == 200) {
                        mView.backToProfile()
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

            override fun onFailure(call: Call<ResponseData<DataItem>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
                t.message?.let { Log.e("onFailure", it) }
            }
        })


    }

}