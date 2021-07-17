package com.descolab.chbpip.service.response

import com.google.gson.annotations.SerializedName

data class Data(

    @field:SerializedName("user")
    val user: DataUser? = null
)