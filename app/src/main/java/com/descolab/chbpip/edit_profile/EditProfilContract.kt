package com.descolab.chbpip.edit_profile

import com.descolab.chbpip.service.response.DataUser
import java.io.File

class EditProfilContract {
    interface View{
        fun showProgressDialog(show: Boolean)
        fun nextStep(user: DataUser)
    }

    interface UserActionListener{
        fun saveUpdate(id:String,
                       name: String,
                         alias: String,
                         lembaga: String,
                         subLembaga: String,
                         satker: String,
                         noId: String,
                         noHp: String,
                         email: String,
                         password: String,
                         foto: File?)
    }
}