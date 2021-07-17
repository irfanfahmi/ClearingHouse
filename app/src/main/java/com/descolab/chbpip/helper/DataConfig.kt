package com.descolab.chbpip.helper

import android.content.SharedPreferences

class DataConfig {
    companion object {
        val USER_ID = "id"
        val NAME = "name"
        val ALIAS = "alias"
        val INSTANSI = "instansi"
        val SATKER = "unit_satker"
        val NO_ID = "no_id"
        val PHONE_NUMBER = "no_hp"
        val EMAIL = "email"
        val PASSWORD = "password"
        val AVATAR = "foto"

        val IDINSTANSI = "id_instansi"
        val IDLEMBAGA = "id_lembaga"

        val IS_LOGIN = "is_login"
        val TOKEN = "token"
        val ROLE_ID = "role_id"




        var mSecurePrefs: SharedPreferences? = null

        fun setString(key: String, value: String) {
            mSecurePrefs!!.edit().putString(key, value).apply()
        }

        fun getString(key: String): String {
            if(mSecurePrefs!=null){
                return mSecurePrefs?.getString(key, "")!!
            }else return ""
        }

        fun setInt(key: String, value: Int) {
            mSecurePrefs?.edit()?.putInt(key, value)?.apply()
        }

        fun getInt(key: String): Int {
            if(mSecurePrefs!=null) {
                return mSecurePrefs?.getInt(key, 0)!!
            }else return 0
        }

        fun setBoolean(key: String, value: Boolean) {
            mSecurePrefs?.edit()?.putBoolean(key, value)?.apply()
        }

        fun getBoolean(key: String): Boolean {
            if(mSecurePrefs!=null) {
                return mSecurePrefs?.getBoolean(key, false)!!
            }else return false
        }

        fun setLogin() {
            mSecurePrefs?.edit()?.putBoolean(IS_LOGIN, true)?.apply()
        }

        fun setLogout() {
            mSecurePrefs?.edit()?.putBoolean(IS_LOGIN, false)?.apply()
        }

        fun isLogin(): Boolean {
            if(mSecurePrefs!=null) {
                return mSecurePrefs?.getBoolean(IS_LOGIN, false)!!
            }else return false
        }

        fun clearAll() {
            mSecurePrefs?.edit()?.clear()?.apply()
        }
    }
}