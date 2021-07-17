package com.descolab.chbpip.service.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseData<T> {

    @SerializedName("code")
    @Expose
    private var code: Int? = null

    @SerializedName("success")
    val success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null
    @SerializedName("data")
    @Expose
    private var data: T? = null

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int?) {
        this.code = code
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getData(): T? {
        return data
    }

    fun setData(data: T) {
        this.data = data
    }
}