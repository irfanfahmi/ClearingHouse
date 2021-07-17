package com.descolab.chbpip.form_kerjasama

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.helper.Utils
import kotlinx.android.synthetic.main.activity_form_kerjasama.*
import java.text.SimpleDateFormat

class FormKerjasamaActivity : AppCompatActivity(),FormKerjasamaContract.View{

    private var progressDialog : ProgressDialog? = null
    private var mActionListener: FormKerjasamaPresenter? = null

     var tipePengajuan : String = "3"

     var id_direktorat_1 : String = ""
     var id_direktorat_2 : String = ""
     var id_direktorat_3 : String = ""
     var id_direktorat_4 : String = ""
     var id_direktorat_5 : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_kerjasama)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = FormKerjasamaPresenter(this, this)


        val saran :String? = intent.getStringExtra("saran")
        Log.d("Cek","Form Kerjasama "+saran)

        if(saran.toString().equals("3")){
            Log.d("Cek","Saran/Masukan/Ajuan "+tipePengajuan)
            Log.d("Cek","tipePengajuan "+tipePengajuan)
            tipePengajuan = "3"
            ivBgLabel.text = "Saran/Masukan/Ajuan"
            tvPerihal.text = "Saran/Masukan/Ajuan"
            ivBgLabel.setBackgroundResource(R.drawable.ic_label_mitra)

        }else{
            Log.d("Cek","Form Kerjasama "+tipePengajuan)
            Log.d("Cek","tipePengajuan "+tipePengajuan)
            tipePengajuan = "1"
            ivBgLabel.text = "Form Pengajuan Kerjasama"
            tvPerihal.text = "Form Pengajuan Kerjasama"
            ivBgLabel.setBackgroundResource(R.drawable.ic_label_non_mitra)

        }


        spinDirektorat1.setOnClickListener {
            displayDirektorat1(it)
        }
        spinDirektorat2.setOnClickListener {
            displayDirektorat2(it)
        }
        spinDirektorat3.setOnClickListener {
            displayDirektorat3(it)
        }
        spinDirektorat4.setOnClickListener {
            displayDirektorat4(it)
        }
        spinDirektorat5.setOnClickListener {
            displayDirektorat5(it)
        }
        tvTanggal.text = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()).toString()
        tvAsalPengusul.text = DataConfig.getString(DataConfig.INSTANSI)
        tvNama.text = DataConfig.getString(DataConfig.NAME)
        tvUnitSatker.text = DataConfig.getString(DataConfig.SATKER)


        btnSubmit.setOnClickListener {

            val d1 = spinDirektorat1.text.toString()+" "
            val d2 = spinDirektorat2.text.toString()+" "
            val d3 = spinDirektorat3.text.toString()+" "
            val d4 = spinDirektorat4.text.toString()+" "
            val d5 = spinDirektorat5.text.toString()
            val kerjasama1 = d1+d2+d3+d4+d5
            if(kerjasama1.trim().isEmpty()){
                Utils.showToast(this,"Kerjasama harus diisi!")
            }else{
                Log.d("Cek","btnSubmit tipePengajuan "+tipePengajuan)

                val i = Intent(this, KonfirmasiPKActivity::class.java)
                i.putExtra("kerjasama",kerjasama1.toString())
                i.putExtra("pesan",etPesan.text.toString())
                i.putExtra("tipe_pengajuan",tipePengajuan)
                i.putExtra("id_direktorat_1",id_direktorat_1)
                i.putExtra("id_direktorat_2",id_direktorat_2)
                i.putExtra("id_direktorat_3",id_direktorat_3)
                i.putExtra("id_direktorat_4",id_direktorat_4)
                i.putExtra("id_direktorat_5",id_direktorat_5)
                startActivity(i)
            }

        }
    }

    fun displayDirektorat1(v: View){
        var listDirektorat = arrayOf<String>(
                "Kelembagaan",
                "Sosialisasi",
                "Pembudayaan",
                "Harmonisasi Per-UU",
                "Advokasi",
                "Institusionalisasi",
                "Pengkajian materi",
                "Materi aparatur negara",
                "Materi formal-non formal-informal",
                "Perencanaan diklat",
                "Kurikulum diklat",
                "Penyelenggaraan diklat",
                "Pengendalian",
                "Evaluasi"
        )
        val builder = AlertDialog.Builder(this)
        builder?.setTitle("Area Kerjasama")
        builder?.setSingleChoiceItems( listDirektorat, -1 ) {
            dialogInterface, i ->
            (v as EditText).setText(listDirektorat[i])
            val jenisLapor = listDirektorat[i]
            Log.d("Cek","Isi id Direktorat "+i.plus(1).toString())
            id_direktorat_1 = i.plus(1).toString()
            dialogInterface.dismiss()
        }
        builder?.show()
    }

    fun displayDirektorat2(v: View){
        var listDirektorat = arrayOf<String>(
                "Kelembagaan",
                "Sosialisasi",
                "Pembudayaan",
                "Harmonisasi Per-UU",
                "Advokasi",
                "Institusionalisasi",
                "Pengkajian materi",
                "Materi aparatur negara",
                "Materi formal-non formal-informal",
                "Perencanaan diklat",
                "Kurikulum diklat",
                "Penyelenggaraan diklat",
                "Pengendalian",
                "Evaluasi"
        )
        val builder = AlertDialog.Builder(this)
        builder?.setTitle("Area Kerjasama")
        builder?.setSingleChoiceItems( listDirektorat, -1 ) {
            dialogInterface, i ->
            (v as EditText).setText(listDirektorat[i])
            val jenisLapor = listDirektorat[i]
            id_direktorat_2 = i.plus(1).toString()

            dialogInterface.dismiss()
        }
        builder?.show()
    }
    fun displayDirektorat3(v: View){
        var listDirektorat = arrayOf<String>(
                "Kelembagaan",
                "Sosialisasi",
                "Pembudayaan",
                "Harmonisasi Per-UU",
                "Advokasi",
                "Institusionalisasi",
                "Pengkajian materi",
                "Materi aparatur negara",
                "Materi formal-non formal-informal",
                "Perencanaan diklat",
                "Kurikulum diklat",
                "Penyelenggaraan diklat",
                "Pengendalian",
                "Evaluasi"
        )
        val builder = AlertDialog.Builder(this)
        builder?.setTitle("Area Kerjasama")
        builder?.setSingleChoiceItems( listDirektorat, -1 ) {
            dialogInterface, i ->
            (v as EditText).setText(listDirektorat[i])
            val jenisLapor = listDirektorat[i]
            id_direktorat_3 = i.plus(1).toString()

            dialogInterface.dismiss()
        }
        builder?.show()
    }
    fun displayDirektorat4(v: View){
        var listDirektorat = arrayOf<String>(
                "Kelembagaan",
                "Sosialisasi",
                "Pembudayaan",
                "Harmonisasi Per-UU",
                "Advokasi",
                "Institusionalisasi",
                "Pengkajian materi",
                "Materi aparatur negara",
                "Materi formal-non formal-informal",
                "Perencanaan diklat",
                "Kurikulum diklat",
                "Penyelenggaraan diklat",
                "Pengendalian",
                "Evaluasi"
        )
        val builder = AlertDialog.Builder(this)
        builder?.setTitle("Area Kerjasama")
        builder?.setSingleChoiceItems( listDirektorat, -1 ) {
            dialogInterface, i ->
            (v as EditText).setText(listDirektorat[i])
            val jenisLapor = listDirektorat[i]
            id_direktorat_4 = i.plus(1).toString()

            dialogInterface.dismiss()
        }
        builder?.show()
    }
    fun displayDirektorat5(v: View){
        var listDirektorat = arrayOf<String>(
                "Kelembagaan",
                "Sosialisasi",
                "Pembudayaan",
                "Harmonisasi Per-UU",
                "Advokasi",
                "Institusionalisasi",
                "Pengkajian materi",
                "Materi aparatur negara",
                "Materi formal-non formal-informal",
                "Perencanaan diklat",
                "Kurikulum diklat",
                "Penyelenggaraan diklat",
                "Pengendalian",
                "Evaluasi"
        )
        val builder = AlertDialog.Builder(this)
        builder?.setTitle("Area Kerjasama")
        builder?.setSingleChoiceItems( listDirektorat, -1 ) {
            dialogInterface, i ->
            (v as EditText).setText(listDirektorat[i])
            val jenisLapor = listDirektorat[i]
            id_direktorat_5 = i.plus(1).toString()

            dialogInterface.dismiss()
        }
        builder?.show()
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun backToProfile() {

    }


}