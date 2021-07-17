package com.descolab.chbpip.home

class HomeContract {
    interface View{
//        fun showTopHeadline(data: ArrayList<ArticlesItem>)
        fun showProgressDialog(show: Boolean)
    }

    interface UserActionListener{
        fun loadTopHeadline()
    }
}