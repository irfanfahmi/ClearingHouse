package com.descolab.chbpip.form_luring

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.descolab.chbpip.R
import com.descolab.chbpip.form_kerjasama.KonfirmasiPKActivity
import com.descolab.chbpip.helper.DataConfig
import kotlinx.android.synthetic.main.activity_form_luring.*
import kotlinx.android.synthetic.main.activity_form_luring.btnSubmit
import kotlinx.android.synthetic.main.activity_form_luring.etPesan
import kotlinx.android.synthetic.main.activity_form_luring.tvAsalPengusul
import kotlinx.android.synthetic.main.activity_form_luring.tvNama
import kotlinx.android.synthetic.main.activity_form_luring.tvTanggal
import kotlinx.android.synthetic.main.activity_form_luring.tvUnitSatker
import java.text.SimpleDateFormat
import java.util.*

class FormLuringActivity : AppCompatActivity(){
    private var progressDialog : ProgressDialog? = null
    private var idDirektorat : String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_luring)
        idDirektorat = intent.getStringExtra("idDirektorat")
        Log.d("Cek","Isi ID Direktorat Luring "+idDirektorat.toString())
        btnBatal.setOnClickListener {
            onBackPressed()
        }

        tvTanggal.text = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()).toString()
        tvAsalPengusul.text = DataConfig.getString(DataConfig.INSTANSI)
        tvNama.text = DataConfig.getString(DataConfig.NAME)
        tvUnitSatker.text = DataConfig.getString(DataConfig.SATKER)

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            etTanggal.setText(sdf.format(cal.time).toString())
        }

        etTanggal.setOnClickListener {
                DatePickerDialog(
                    this, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            val myFormat = "dd/MM/yyyy hh.mm" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            etWaktu.setText(sdf.format(cal.time).toString())
        }
        etWaktu.setOnClickListener {
            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    cal.set(Calendar.MINUTE, minute)
                    val myFormat = "HH.mm" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
                    etWaktu.setText(sdf.format(cal.time).toString())
                },  cal.get(Calendar.HOUR_OF_DAY),  cal.get(Calendar.MINUTE), true
            )
            timePickerDialog.show()
        }
        etJenisPertemuan.setOnClickListener {
            displayJenisPertemuan(it)
        }

        btnSubmit.setOnClickListener {
            val i = Intent(this, KonfirmasiPKActivity::class.java)
            i.putExtra("tema",etPesan.text.toString())
            i.putExtra("perihal",etPerihal.text.toString())
            i.putExtra("tanggal",etTanggal.text.toString())
            i.putExtra("waktu",etWaktu.text.toString())
            i.putExtra("jenis",etJenisPertemuan.text.toString())
            i.putExtra("tipe_pengajuan","2")
            i.putExtra("idDirektorat",idDirektorat.toString())

            startActivity(i)
        }



    }
    fun displayJenisPertemuan(v: View){
        var listJenisPertemuan = arrayOf<String>("Luring",
            "Daring"
        )
        val builder = AlertDialog.Builder(this)
        builder?.setTitle("Jenis Pertemuan")
        builder?.setSingleChoiceItems( listJenisPertemuan, -1 ) {
                dialogInterface, i ->
            (v as EditText).setText(listJenisPertemuan[i])

            dialogInterface.dismiss()
        }
        builder?.show()
    }


}