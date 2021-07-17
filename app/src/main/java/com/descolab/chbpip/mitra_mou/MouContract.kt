package com.descolab.chbpip.mitra_mou

import com.descolab.chbpip.service.response.DataItem

class MouContract {
    interface View{
        fun showMou(data: ArrayList<DataItem>)
        fun showProgressDialog(show: Boolean)
    }

    interface UserActionListener{
        fun loadMou(id_user: String,idInstansi: String,idLembaga: String)
        fun loadPks(id_user: String,idInstansi: String,idLembaga: String)
        fun loadMonev(id_user: String,idInstansi: String,idLembaga: String)
    }
}