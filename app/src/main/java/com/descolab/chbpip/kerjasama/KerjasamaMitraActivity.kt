package com.descolab.chbpip.kerjasama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.main.MainActivity
import com.descolab.chbpip.mitra_mou.MouActivity
import kotlinx.android.synthetic.main.activity_kerjasama_mitra.*

class KerjasamaMitraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kerjasama_mitra)

        della_nyapa.text = "Haii.. "+ DataConfig.getString(DataConfig.NAME)+" Selamat datang Apa yang bisa saya bantu?"

        Handler().postDelayed(Runnable {
            imageView4.visibility = View.VISIBLE
            imageView3.visibility = View.VISIBLE
            della_nyapa.visibility = View.VISIBLE
        }, 1500)

        Handler().postDelayed(Runnable {
            imageView6.visibility = View.VISIBLE
            imageView5.visibility = View.VISIBLE
            della_terimakasih.visibility = View.VISIBLE
        }, 3000)

        Handler().postDelayed(Runnable {
            imageView8.visibility = View.VISIBLE
            imageView7.visibility = View.VISIBLE
            della_pilih_kerjasama.visibility = View.VISIBLE
        }, 4500)

        Handler().postDelayed(Runnable {
            btnMOU.visibility = View.VISIBLE
            btnPks.visibility = View.VISIBLE
            btnMonev.visibility = View.VISIBLE
        }, 4800)

        ivLabelMitra.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        btnMOU.setOnClickListener {
            val i = Intent(this, MouActivity::class.java)
            i.putExtra("code","1")
            startActivity(i)
        }
        btnPks.setOnClickListener {
            val i = Intent(this, MouActivity::class.java)
            i.putExtra("code","2")
            startActivity(i)

        }
        btnMonev.setOnClickListener {
            val i = Intent(this, MouActivity::class.java)
            i.putExtra("code","3")
            startActivity(i)
        }
    }
}