package com.descolab.chbpip.login

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.descolab.chbpip.App
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.main.MainActivity
import com.descolab.chbpip.service.response.DataUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),LoginContract.View {
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: LoginPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = LoginPresenter(this, this)

        btnLogin.setOnClickListener {
            mActionListener!!.login(
                etNoHp.text.toString(),
                etPassword.text.toString()
            )
        }
        DataConfig.mSecurePrefs = App.get().defaultSharedPreferences
        App.get().sharedPreferences1000
        if(DataConfig.isLogin()){
            val home = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(home)
            finish()
        }

    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun enterMainActivity(data: DataUser) {
        data.id?.let { DataConfig.setString(DataConfig.USER_ID, it) }
        data.name?.let { DataConfig.setString(DataConfig.NAME, it) }
        data.alias?.let { DataConfig.setString(DataConfig.ALIAS, it) }
        data.instansi?.let { DataConfig.setString(DataConfig.INSTANSI, it) }
        data.unitSatker?.let { DataConfig.setString(DataConfig.SATKER, it) }
        data.noId?.let { DataConfig.setString(DataConfig.NO_ID, it) }
        data.noHp?.let { DataConfig.setString(DataConfig.PHONE_NUMBER, it) }
        data.email?.let { DataConfig.setString(DataConfig.EMAIL, it) }
        data.password?.let { DataConfig.setString(DataConfig.PASSWORD, it) }
        data.foto?.let { DataConfig.setString(DataConfig.AVATAR, it) }
        //todo roleid_type user 1 non mitra 2 mitra
        data.roleUser?.let { DataConfig.setString(DataConfig.ROLE_ID, it) }
        data.idInstansi?.let { DataConfig.setString(DataConfig.IDINSTANSI, it) }
        data.idLembaga?.let { DataConfig.setString(DataConfig.IDLEMBAGA, it) }
        DataConfig.setLogin()
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage("Login Berhasil")
        builder.setCancelable(false)
        builder.setPositiveButton("OK"){dialog, which ->
            dialog.dismiss()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}