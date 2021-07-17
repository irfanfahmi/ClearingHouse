package com.descolab.chbpip.inbox

import com.descolab.chbpip.service.response.DataItem

class InboxContract {
    interface View{
        fun showInbox(data: ArrayList<DataItem>)
        fun showProgressDialog(show: Boolean)
    }

    interface UserActionListener{
        fun loadInbox(id: String)
    }
}