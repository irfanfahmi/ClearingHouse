package com.descolab.chbpip.list_direktorat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.descolab.chbpip.R
import com.descolab.chbpip.form_kerjasama.FormKerjasamaActivity
import com.descolab.chbpip.helper.DataConfig
import kotlinx.android.synthetic.main.activity_detail_direktorat.*
import kotlinx.android.synthetic.main.activity_detail_direktorat.ivLabelMitra
import kotlinx.android.synthetic.main.fragment_profile.*

class DetailDirektoratActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_direktorat)
        val id :String? = intent.getStringExtra("id")
        val title :String? = intent.getStringExtra("title")
        val desc :String? = intent.getStringExtra("desc")
        penjelasanDella.text = "Haii.."+ DataConfig.getString(DataConfig.NAME) +" berikut  penjelasan tugas Direktorat "+title
        tvDescDirektorat.text = desc.toString()
        ivLabelMitra.setOnClickListener {
            onBackPressed()
        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
        if(DataConfig.getString(DataConfig.ROLE_ID).equals("1")){
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_non_mitra)
        }else{
            ivLabelMitra.setBackgroundResource(R.drawable.ic_label_mitra)
        }
        btnIsiForm.setOnClickListener {
            val i = Intent(this, FormKerjasamaActivity::class.java)
            startActivity(i)
        }

    }
}