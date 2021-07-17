package com.descolab.chbpip.login

import com.descolab.chbpip.service.response.DataUser


class LoginContract {

    interface View{
        fun showProgressDialog(show: Boolean)
        fun enterMainActivity(user: DataUser)
    }

    interface UserActionListener{
        fun login(phone: String, password: String)
    }

}