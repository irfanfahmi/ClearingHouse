package com.descolab.chbpip.form_kerjasama

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.main.MainActivity
import kotlinx.android.synthetic.main.activity_konfirmasi_p_k.*
import kotlinx.android.synthetic.main.activity_konfirmasi_p_k.imageView3
import kotlinx.android.synthetic.main.activity_konfirmasi_p_k.imageView4
import kotlinx.android.synthetic.main.activity_konfirmasi_p_k.imageView5
import kotlinx.android.synthetic.main.activity_konfirmasi_p_k.imageView6
import java.text.SimpleDateFormat

class KonfirmasiPKActivity : AppCompatActivity(),FormKerjasamaContract.View{
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: FormKerjasamaPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_p_k)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = FormKerjasamaPresenter(this, this)
        val isikerjasama :String? = intent.getStringExtra("kerjasama")
        val pesan :String? = intent.getStringExtra("pesan")
        val tipePengajuan :String? = intent.getStringExtra("tipe_pengajuan")

        val idDirektorat1 :String? = intent.getStringExtra("id_direktorat_1")
        val idDirektorat2 :String? = intent.getStringExtra("id_direktorat_2")
        val idDirektorat3 :String? = intent.getStringExtra("id_direktorat_3")
        val idDirektorat4 :String? = intent.getStringExtra("id_direktorat_4")
        val idDirektorat5 :String? = intent.getStringExtra("id_direktorat_5")

        val pengajuanSaran :String? = intent.getStringExtra("pengajuan_saran")


        val perihal :String? = intent.getStringExtra("perihal")
        val tema :String? = intent.getStringExtra("tema")
        val tanggal :String? = intent.getStringExtra("tanggal")
        val waktu :String? = intent.getStringExtra("waktu")
        val jenis :String? = intent.getStringExtra("jenis")
        val idDirektorat :String? = intent.getStringExtra("idDirektorat")

        Log.d("cek","isi tipePengajuan "+tipePengajuan)
        Log.d("cek","isi Waktu "+waktu)
        Log.d("cek","isi Kerjasama "+isikerjasama)
        Log.d("cek","isi Pesan "+pesan)
        Log.d("cek","------------------------------------")
        Log.d("cek","isi idDirektorat1 "+idDirektorat1)
        Log.d("cek","isi idDirektorat2 "+idDirektorat2)
        Log.d("cek","isi idDirektorat3 "+idDirektorat3)
        Log.d("cek","isi idDirektorat4 "+idDirektorat4)
        Log.d("cek","isi idDirektorat5 "+idDirektorat5)
        Log.d("cek","------------------------------------")




        if (tipePengajuan.toString().equals("1")){
            confirm_pengajuan_della.text = getString(R.string.konfirmasi_pengajuan_della1)
            tvTerimakasih.text = getString(R.string.terimakasih_pengajuan_della1)
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_non_mitra)
        }else if (tipePengajuan.toString().equals("2")){
            confirm_pengajuan_della.text = getString(R.string.konfirmasi_pengajuan_della2)
            tvTerimakasih.text = getString(R.string.terimakasih_pengajuan_della2)
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_mitra)

        }else{
            confirm_pengajuan_della.text = getString(R.string.konfirmasi_pengajuan_della3)
            tvTerimakasih.text = getString(R.string.terimakasih_pengajuan_della3)
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_mitra)
        }

        ivLabelMitra.setOnClickListener {
            onBackPressed()
        }


        Handler().postDelayed(Runnable {
            imageView4.visibility = View.VISIBLE
            imageView3.visibility = View.VISIBLE
            confirm_pengajuan_della.visibility = View.VISIBLE
            btnYa.visibility = View.VISIBLE
            btnTidak.visibility = View.VISIBLE
        }, 1500)


        btnYa.setOnClickListener {
            if (tipePengajuan.toString().equals("1")){
                //Form Kerjasama
                Handler().postDelayed(Runnable {
                    btnYa.visibility = View.GONE
                    btnTidak.visibility = View.GONE
                    imageView5.visibility = View.VISIBLE
                    imageView6.visibility = View.VISIBLE
                    tvTerimakasih.visibility = View.VISIBLE
                }, 1000)
                Handler().postDelayed(Runnable {
                    mActionListener!!.submitPengajuan(
                        "Usulan Kerjasama",
                        SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()).toString(),
                        DataConfig.getString(DataConfig.INSTANSI),
                        DataConfig.getString(DataConfig.NAME),
                        DataConfig.getString(DataConfig.SATKER),
                        isikerjasama.toString(),
                        pesan.toString(),
                        "1",
                        "1",
                        idDirektorat1,
                        idDirektorat2,
                        idDirektorat3,
                        idDirektorat4,
                        idDirektorat5,
                        DataConfig.getString(DataConfig.USER_ID)
                    )
                }, 2000)
            }else if (tipePengajuan.toString().equals("2")){
                //Form Luring
                Handler().postDelayed(Runnable {
                    btnYa.visibility = View.GONE
                    btnTidak.visibility = View.GONE
                    imageView5.visibility = View.VISIBLE
                    imageView6.visibility = View.VISIBLE
                    tvTerimakasih.visibility = View.VISIBLE
                }, 1000)
                Handler().postDelayed(Runnable {
                    mActionListener!!.submitPengajuanLuring(
                        perihal.toString(),
                        tanggal.toString(),
                        waktu.toString(),
                        DataConfig.getString(DataConfig.INSTANSI),
                        DataConfig.getString(DataConfig.NAME),
                        DataConfig.getString(DataConfig.SATKER),
                        tema.toString(),
                        jenis.toString(),
                        "1",
                        "2",
                        idDirektorat.toString(),
                        DataConfig.getString(DataConfig.USER_ID)
                    )
                }, 2000)
            }else{
                //Form Sarandan Masukan
                Handler().postDelayed(Runnable {
                    btnYa.visibility = View.GONE
                    btnTidak.visibility = View.GONE
                    imageView5.visibility = View.VISIBLE
                    imageView6.visibility = View.VISIBLE
                    tvTerimakasih.visibility = View.VISIBLE
                }, 1000)
                Handler().postDelayed(Runnable {
                    mActionListener!!.submitPengajuan(
                        "Saran/Masukan/Aduan",
                        SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()).toString(),
                        DataConfig.getString(DataConfig.INSTANSI),
                        DataConfig.getString(DataConfig.NAME),
                        DataConfig.getString(DataConfig.SATKER),
                        isikerjasama.toString(),
                        pesan.toString(),
                        "3",
                        "3",
                            idDirektorat1,
                            idDirektorat2,
                            idDirektorat3,
                            idDirektorat4,
                            idDirektorat5,
                        DataConfig.getString(DataConfig.USER_ID)
                    )
                }, 2000)
            }


        }

        btnTidak.setOnClickListener {
            onBackPressed()
        }
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun backToProfile() {

        Handler().postDelayed(Runnable {
            //setelah loading maka akan langsung berpindah ke home activity
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)
    }
}