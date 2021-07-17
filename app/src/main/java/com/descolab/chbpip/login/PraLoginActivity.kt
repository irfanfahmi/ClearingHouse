package com.descolab.chbpip.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.descolab.chbpip.App
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.main.MainActivity
import kotlinx.android.synthetic.main.activity_pra_login.*

class PraLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pra_login)

        btnMitra.setOnClickListener {
           val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        btnNonMitra.setOnClickListener {
            val i = Intent(this, ChooseRegLogActivity::class.java)
            startActivity(i)
        }

        DataConfig.mSecurePrefs = App.get().defaultSharedPreferences
        App.get().sharedPreferences1000
        if(DataConfig.isLogin()){
            val home = Intent(this@PraLoginActivity, MainActivity::class.java)
            startActivity(home)
            finish()
        }

    }
}