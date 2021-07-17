package com.descolab.chbpip.form_kerjasama

class FormKerjasamaContract {
    interface View{
        fun showProgressDialog(show: Boolean)
        fun backToProfile()

    }

    interface UserActionListener{
        fun submitPengajuan(
                perihal: String,
                tanggal: String,
                asal_pengusul: String,
                nama: String,
                unit_satker: String,
                kerjasama: String,
                pesan : String,
                status: String,
                tipe_pengajuan: String,
                id_direktorat_1: String?,
                id_direktorat_2: String?,
                id_direktorat_3: String?,
                id_direktorat_4: String?,
                id_direktorat_5: String?,
                id_user: String
        )
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
            tipe_pengajuan: String,
            idDirektorat: String?,
            id_user: String
        )
    }
}