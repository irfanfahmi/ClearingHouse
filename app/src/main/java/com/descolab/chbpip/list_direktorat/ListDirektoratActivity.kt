package com.descolab.chbpip.list_direktorat

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.chbpip.R
import com.descolab.chbpip.form_kerjasama.FormKerjasamaActivity
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.helper.Utils
import com.descolab.chbpip.main.MainActivity
import com.descolab.chbpip.service.response.DataItem
import kotlinx.android.synthetic.main.activity_list_direktorat.*
import kotlinx.android.synthetic.main.activity_list_direktorat.ivLabelMitra
import kotlinx.android.synthetic.main.fragment_profile.*

class ListDirektoratActivity : AppCompatActivity(), ListDirektoratContract.View,ListDirektoratAdapter.ListDirektoratListener {

    private var progressDialog : ProgressDialog? = null
    private var mActionListener: ListDirektoratPresenter? = null
    private var mAdapter: ListDirektoratAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_direktorat)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = ListDirektoratPresenter(this, this)
        mActionListener?.loadDirektorat()
        kerjasamaDella.text = "Hai.."+ DataConfig.getString(DataConfig.NAME)+" Selamat Datang\n" +
                "Silahkan Pilih Area kerjasama yang anda inginkan"
        btnIsiForm.setOnClickListener {
            val formKerjaSama = Intent(this, FormKerjasamaActivity::class.java)
            startActivity(formKerjaSama)
        }
        ivLabelMitra.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        if(DataConfig.getString(DataConfig.ROLE_ID).equals("1")){
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_non_mitra)
        }else{
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_mitra)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun showListDirektorat(data: ArrayList<DataItem>) {
        if(data.isNotEmpty()){
            mAdapter = ListDirektoratAdapter(this, data, this)
            rvDirektorat?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvDirektorat?.setHasFixedSize(true)
            rvDirektorat?.adapter = mAdapter
        }else{
            Utils.showToast(this, "Hasil pencarian tidak ditemukan")
        }

    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun toDetailDirektorat(item: DataItem) {
            val i = Intent(this, DetailDirektoratActivity::class.java)
            i.putExtra("id",item.id.toString())
            i.putExtra("title",item.nama.toString())
            i.putExtra("desc",item.deskripsi.toString())
            startActivity(i)
    }
}