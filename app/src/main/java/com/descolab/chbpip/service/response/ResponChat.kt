package com.descolab.chbpip.service.response

import com.google.gson.annotations.SerializedName

data class ResponseChat<T>(
    @field:SerializedName("data")
    val data: T? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,
    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("message")
    val message: String? = null
)