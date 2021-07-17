package com.descolab.chbpip.service.response

import com.google.gson.annotations.SerializedName

data class DataUser(

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("no_hp")
    val noHp: String? = null,

    @field:SerializedName("foto")
    val foto: String? = null,

    @field:SerializedName("unit_satker")
    val unitSatker: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("alias")
    val alias: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("no_id")
    val noId: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("instansi")
    val instansi: String? = null,

    @field:SerializedName("role_user")
    val roleUser: String? = null,

    @field:SerializedName("id_instansi")
    val idInstansi: String? = null,

    @field:SerializedName("id_lembaga")
    val idLembaga: String? = null,

)