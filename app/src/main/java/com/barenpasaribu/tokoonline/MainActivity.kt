package com.barenpasaribu.tokoonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.barenpasaribu.tokoonline.activity.MasukActivity
import com.barenpasaribu.tokoonline.fragment.AccountFragment
import com.barenpasaribu.tokoonline.fragment.HomeFragment
import com.barenpasaribu.tokoonline.fragment.ShoppingFragment
import com.barenpasaribu.tokoonline.helper.SharedPref
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var selectedFr: Fragment = HomeFragment() // set active fragment first
    private lateinit var pref: SharedPref // initial variable pref for SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = SharedPref(this)

        nav_view.setOnNavigationItemSelectedListener(onBottomNavigationListener)
        onFragmentAdd()

    }

    private val onBottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.navigation_home -> {
                selectedFr = HomeFragment()
            }
            R.id.navigation_shopping -> {
                selectedFr = ShoppingFragment()
            }
            R.id.navigation_account -> {
                if (pref.getStatusLogin()) {
                    selectedFr = AccountFragment()
                } else {
                    startActivity(Intent(this, MasukActivity::class.java))
                }
            }
        }
        onFragmentReplace()
        true
    }

    private fun onFragmentAdd() {
        val fm = supportFragmentManager.beginTransaction()
        fm.add(R.id.container_fragment, HomeFragment())
        fm.commit()
    }

    private fun onFragmentReplace() {
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.container_fragment, selectedFr)
        fm.commit()
    }

}