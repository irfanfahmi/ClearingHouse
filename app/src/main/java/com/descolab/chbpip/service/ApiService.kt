package com.descolab.chbpip.service


import com.descolab.chbpip.service.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface ApiService {

    @GET(ApiURL.GET_DIREKTORAT)
    fun getListDirektorat(): Call<ResponseData<ArrayList<DataItem>>>

    @Multipart
    @POST(ApiURL.POST_PENGAJUAN_KERJASAMA)
    fun postPengajuanKerjasama(
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Call<ResponseData<DataItem>>

    @Multipart
    @POST(ApiURL.POST_PENGAJUAN_PERTEMUAN)
    fun postPengajuanPertemuan(
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Call<ResponseData<DataItem>>

    @POST(ApiURL.POST_CHAT)
    fun postPercakapan(
        @Query("from") from: String?,
        @Query("isi") isi: String?,
        @Query("to") to: String?,
        @Query("tipePengajuan") tipePengajuan: String?,
    ): Call<ResponseChat<Chat>>


    @GET(ApiURL.GET_INBOX)
    fun getInbox(
        @QueryMap params: Map<String, String>
    ): Call<ResponseData<ArrayList<DataItem>>>

    @Multipart
    @POST(ApiURL.POST_REGISTER)
    fun postRegister(
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part gambar0: MultipartBody.Part
    ): Call<ResponseData<DataUser>>

    @Headers( "Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("http://ch.lurredev.com/api/penguna-register?")
    fun postRegis(
        @FieldMap params: Map<String, String>,
    ): Call<ResponseData<DataUser>>
//todo setAPI REGISTER

//    @Headers( "Content-Type: application/x-www-form-urlencoded")
//    @Multipart
//    @POST("http://ch.lurredev.com/api/penguna-register?")
//    fun postRegis(
//        @Part("name") name: String,
//        @Part("alias") alias: String,
//        @Part("instansi") instansi: String,
//        @Part("unit_satker") unit_satker: String,
//        @Part("no_id") no_id: String,
//        @Part("no_hp") no_hp: String,
//        @Part("email") email: String,
//        @Part("password") password: String,
//        @Part("foto") foto: File?,
//    ): Call<ResponseData<DataUser>>

    @Multipart
    @POST(ApiURL.POST_LOGIN)
    fun postLogin(
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Call<ResponseData<DataUser>>

    @GET(ApiURL.GET_MOU)
    fun getMou(@QueryMap params: Map<String, String>): Call<ResponseData<ArrayList<DataItem>>>

    @GET(ApiURL.GET_PKS)
    fun getPks(@QueryMap params: Map<String, String>): Call<ResponseData<ArrayList<DataItem>>>

    @GET(ApiURL.GET_MONEV)
    fun getMonev(@QueryMap params: Map<String, String>): Call<ResponseData<ArrayList<DataItem>>>

    @GET(ApiURL.GET_CHAT)
    fun getPercakapan(
        @QueryMap params: Map<String, String>
    ): Call<ResponseData<ArrayList<DataItem>>>




    @GET(ApiURL.GET_LEMBAGA)
    fun getLembaga(): Call<ResponseData<ArrayList<DataItem>>>

    @GET(ApiURL.GET_SUB_LEMBAGA)
    fun getSubLembaga(
        @QueryMap params: Map<String, String>
    ): Call<ResponseData<ArrayList<DataItem>>>

    @Multipart
    @POST(ApiURL.POST_UPDATE_USER)
    fun postUpdateUser(
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part gambar0: MultipartBody.Part
    ): Call<ResponseData<DataUser>>

    @Multipart
    @POST(ApiURL.POST_UPDATE_USER)
    fun postUpdateUser(
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>,
    ): Call<ResponseData<DataUser>>


}
