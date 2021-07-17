package com.descolab.chbpip.register

import com.descolab.chbpip.service.response.DataItem
import com.descolab.chbpip.service.response.DataUser
import java.io.File

class RegisterContract {
    interface View{
        fun showProgressDialog(show: Boolean)
        fun nextStep(user: DataUser)
        fun showLembaga(data: ArrayList<DataItem>)
        fun showSubLembaga(data: ArrayList<DataItem>)
    }

    interface UserActionListener{
        fun saveregister(name: String,
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
                         foto: File?)
        fun loadLembaga()
        fun loadSubLembaga(id: String)
    }
}