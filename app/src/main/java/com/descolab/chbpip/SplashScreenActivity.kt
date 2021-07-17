package com.descolab.chbpip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.descolab.chbpip.login.PraLoginActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed(Runnable {
            //setelah loading maka akan langsung berpindah ke home activity
            val login = Intent(this@SplashScreenActivity, PraLoginActivity::class.java)
            startActivity(login)
            finish()
        }, 2000)
    }
}