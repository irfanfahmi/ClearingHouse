package com.descolab.chbpip.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Html
import com.descolab.chbpip.R
import com.descolab.chbpip.kerjasama.KerjasamaMitraActivity
import com.descolab.chbpip.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash_della2.ivLabelMitra
import kotlinx.android.synthetic.main.activity_splash_della2.tvPekenalan

class SplashDella2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_della2)
        tvPekenalan.setText(Html.fromHtml(getString(R.string.perkenalan_della2)));
        Handler().postDelayed(Runnable {
            val direktorat = Intent(this, KerjasamaMitraActivity::class.java)
            startActivity(direktorat)
        }, 1000)
        ivLabelMitra.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}