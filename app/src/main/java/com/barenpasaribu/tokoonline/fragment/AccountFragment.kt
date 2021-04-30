package com.barenpasaribu.tokoonline.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.barenpasaribu.tokoonline.R
import com.barenpasaribu.tokoonline.activity.LoginActivity
import com.barenpasaribu.tokoonline.helper.SharedPref

class AccountFragment : Fragment() {

    private lateinit var pref: SharedPref
    private lateinit var btnLogout: TextView
    private lateinit var tvName: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvEmail: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_account, container,
            false
        )  // Inflate the layout for this fragment

        init(view)
        pref = SharedPref(requireActivity()) // initial pref
        btnLogout.setOnClickListener {
            pref.setStatusLogin(false)
            myIntent()
        }
        setData()
        return view
    }

    private fun setData() {
        if (pref.getUser() == null) {
            myIntent()
        }
        val user = pref.getUser()!!
        tvName.text = user.name
        tvPhone.text = user.phone
        tvEmail.text = user.email
    }

    private fun init(view: View) {
        tvName = view.findViewById(R.id.tv_name)
        tvPhone = view.findViewById(R.id.tv_phone)
        tvEmail = view.findViewById(R.id.tv_email)
        btnLogout = view.findViewById(R.id.btn_logout)
    }

    private fun myIntent() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        return
    }

}