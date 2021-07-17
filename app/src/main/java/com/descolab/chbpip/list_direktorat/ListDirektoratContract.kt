package com.descolab.chbpip.list_direktorat

import com.descolab.chbpip.service.response.DataItem

class ListDirektoratContract {
    interface View{
        fun showListDirektorat(data: ArrayList<DataItem>)
        fun showProgressDialog(show: Boolean)
    }

    interface UserActionListener{
        fun loadDirektorat()
    }
}