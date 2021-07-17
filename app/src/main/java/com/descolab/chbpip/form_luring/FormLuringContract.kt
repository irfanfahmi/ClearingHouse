package com.descolab.chbpip.form_luring

class FormLuringContract {
    interface View{
        fun showProgressDialog(show: Boolean)
        fun backToProfile()
    }

    interface UserActionListener{
        fun submitPengajuanLuring(
                perihal: String,
                tanggal: String,
                jam: String,
                asal_pengusul: String,
                nama: String,
                unit_satker: String,
                tema: String,
                jenisPertemuan : String,
                status: String,
                id_user: String
        )
    }
}