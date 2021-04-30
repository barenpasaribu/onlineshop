package com.barenpasaribu.tokoonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.barenpasaribu.tokoonline.R
import com.barenpasaribu.tokoonline.app.ApiConfig
import com.barenpasaribu.tokoonline.model.ResponseModel
import com.barenpasaribu.tokoonline.helper.SharedPref
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityRegister : AppCompatActivity() {

  lateinit var pref: SharedPref

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_register)

    pref = SharedPref(this)
    btn_register.setOnClickListener {
      registerUser()
    }
  }

  private fun registerUser() {
    if (edt_name.text?.isEmpty()!!) {
      edt_name.error = "Name cannot empty"
      edt_name.requestFocus()
      return
    } else if (edt_email.text?.isEmpty()!!) {
      edt_email.error = "Email cannot empty"
      edt_email.requestFocus()
      return
    } else if (edt_phone.text?.isEmpty()!!) {
      edt_phone.error = "Phone number cannot empty"
      edt_phone.requestFocus()
      return
    } else if (edt_password.text?.isEmpty()!!) {
      edt_password.error = "Password cannot empty"
      edt_password.requestFocus()
      return
    }
    pb_register.visibility = View.VISIBLE
    ApiConfig.instanceRetrofit.register(
      edt_name.text.toString(),
      edt_phone.text.toString(),
      edt_email.text.toString(),
      edt_password.text.toString()
    )
      .enqueue(object : Callback<ResponseModel> {
        override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
          pb_register.visibility = View.GONE
          Toast.makeText(this@ActivityRegister, t.message, Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
          val responseData = response.body()!!
          if (responseData.success) {
            Toast.makeText(this@ActivityRegister, responseData.message, Toast.LENGTH_SHORT).show()
            pb_register.visibility = View.GONE
            pref.setStatusLogin(responseData.success)
            val intent = Intent(this@ActivityRegister, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
          } else {
            Toast.makeText(this@ActivityRegister, responseData.message, Toast.LENGTH_SHORT).show()
          }
        }
      })
  }
}
