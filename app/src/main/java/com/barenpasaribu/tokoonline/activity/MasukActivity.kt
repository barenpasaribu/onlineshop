package com.barenpasaribu.tokoonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.barenpasaribu.tokoonline.R
import com.barenpasaribu.tokoonline.helper.SharedPref
import kotlinx.android.synthetic.main.activity_masuk.*

class MasukActivity : AppCompatActivity() {

    private lateinit var pref: SharedPref // create variable pref -> sharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        pref = SharedPref(this) // init value pref use SharedPref
        mainButton()
    }

    private fun mainButton() {
        btn_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btn_register.setOnClickListener {
            startActivity(Intent(this, ActivityRegister::class.java))
        }
    }
}