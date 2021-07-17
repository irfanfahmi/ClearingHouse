package com.descolab.chbpip.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import com.descolab.chbpip.App
import com.descolab.chbpip.R
import com.descolab.chbpip.helper.DataConfig
import com.descolab.chbpip.helper.Utils
import com.descolab.chbpip.home.HomeFragment
import com.descolab.chbpip.inbox.InboxFragment
import com.descolab.chbpip.media.MediaFragment
import com.descolab.chbpip.profile.ProfileFrag
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic lateinit var instance: MainActivity
        @JvmStatic var popup = 0
    }

    init {
        instance = this
    }

    private var menu: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_container, HomeFragment(), null)
        transaction.commit()

        DataConfig.mSecurePrefs = App.get().defaultSharedPreferences
        App.get().sharedPreferences1000


        navigation.enableShiftingMode(false)
        navigation.enableItemShiftingMode(false)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)
        navigation.onNavigationItemSelectedListener = null
        currentFragment?.let { setBottomMenu(it) }
        navigation.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }

    override fun onResume() {
        super.onResume()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)
        navigation.onNavigationItemSelectedListener = null
        currentFragment?.let { setBottomMenu(it) }
        navigation.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is HomeFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {
                            Utils.addFragment(
                                it,
                                HomeFragment(), null
                            )
                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_media -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is MediaFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {

                            Utils.addFragment(
                                it,
                                MediaFragment(), null
                            )
                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_inbox -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is InboxFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {

                            Utils.addFragment(
                                it,
                                InboxFragment(), null
                            )
                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_profil -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is ProfileFrag) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {

                            Utils.addFragment(
                                it,
                                ProfileFrag(), null
                            )

                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    fun setBottomMenu(fragment: Fragment) {
        navigation.visibility = View.VISIBLE
        if (fragment is HomeFragment) {
            navigation.currentItem = 0
        } else if (fragment is MediaFragment) {
            navigation.currentItem = 1
        }else if (fragment is InboxFragment) {
            navigation.currentItem = 2
        } else if (fragment is ProfileFrag) {
            navigation.currentItem = 3
        } else {
            navigation.visibility = View.GONE
        }
    }
}