package com.descolab.chbpip.service.response

import com.google.gson.annotations.SerializedName

data class Chat(

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("from")
    val from: String? = null,

    @field:SerializedName("to")
    val to: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("is_seen")
    val isSeen: Int? = null,

    @field:SerializedName("isi")
    val isi: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
)
