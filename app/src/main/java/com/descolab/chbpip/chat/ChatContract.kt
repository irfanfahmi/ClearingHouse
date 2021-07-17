package com.descolab.chbpip.chat

import com.descolab.chbpip.service.response.DataItem

class ChatContract {
    interface View {
        fun showPercakapan()
        fun showCekPercakapan(data: ArrayList<DataItem>)
        fun showCekUpdatePercakapan(data: ArrayList<DataItem>)
        fun showProgressDialog(show: Boolean)
        fun showListDirektorat(data: ArrayList<DataItem>)

    }

    interface UserActionListener {
        fun loadHistory(idPengguna :String)
        fun loadDirektorat()
        fun cekPercakapan(idPengguna :String, idDirektorat: String)
        fun cekUpdatePercakapan(idPengguna :String, idDirektorat: String)
        fun postPercakapan(idPengguna :String, message: String, idDirektorat: String, tipePengajuan: String)

    }
}