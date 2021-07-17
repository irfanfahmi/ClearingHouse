package com.descolab.chbpip.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Html
import com.descolab.chbpip.R
import com.descolab.chbpip.list_direktorat.ListDirektoratActivity
import com.descolab.chbpip.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash_della1.*
import kotlinx.android.synthetic.main.activity_splash_della1.ivLabelMitra

class SplashDella1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_della1)
        tvPekenalan.setText(Html.fromHtml(getString(R.string.perkenalan_della)));
        Handler().postDelayed(Runnable {
            val direktorat = Intent(this, ListDirektoratActivity::class.java)
            startActivity(direktorat)
        }, 1000)
        ivLabelMitra.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}