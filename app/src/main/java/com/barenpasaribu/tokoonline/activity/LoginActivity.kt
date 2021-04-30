package com.barenpasaribu.tokoonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.barenpasaribu.tokoonline.MainActivity
import com.barenpasaribu.tokoonline.R
import com.barenpasaribu.tokoonline.app.ApiConfig
import com.barenpasaribu.tokoonline.model.ResponseModel
import com.barenpasaribu.tokoonline.helper.SharedPref
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var pref: SharedPref   // create variable pref -> sharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        pref = SharedPref(this)  // init value pref use SharedPref
        btn_login.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        if (edt_email_login.text?.isEmpty()!!) {
            edt_email_login.error = "Name cannot be empty"
            edt_email_login.requestFocus()
            return
        } else if (edt_password_login.text?.isEmpty()!!) {
            edt_password_login.error = "Password cannot be empty"
            edt_email_login.requestFocus()
            return
        }
        pb_login.visibility = View.VISIBLE // progress bar visible
        ApiConfig.instanceRetrofit.login(
            edt_email_login.text.toString(), edt_password_login.text.toString()
        )
            .enqueue(object : Callback<ResponseModel> {
                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    pb_login.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    pb_login.visibility = View.GONE
                    val responseData = response.body()!!
                    if (responseData.success) {
                        pref.setStatusLogin(responseData.success)
                        pref.setUser(responseData.user)
                        myIntent()
                        Toast.makeText(this@LoginActivity, responseData.message, Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(this@LoginActivity, responseData.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }

    fun myIntent() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}


