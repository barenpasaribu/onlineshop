package com.barenpasaribu.tokoonline.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.barenpasaribu.tokoonline.model.User
import com.google.gson.Gson

class SharedPref(activity: Activity) {
    private val myPref = "MAIN_PREF"
    private val sp: SharedPreferences

    private val statusLogin = "login"
    private val user = "user"

    init {
        sp = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE)
    }

    // set status login
    fun setStatusLogin(status: Boolean) {
        sp.edit().putBoolean(statusLogin, status).apply()
    }

    // get status login
    fun getStatusLogin(): Boolean {
        return sp.getBoolean(statusLogin, false)
    }

    // set data user from string to json
    fun setUser(value: User) {
        val data: String = Gson().toJson(value, User::class.java)
        return sp.edit().putString(user, data).apply()
    }

    // get data user from json to string
    fun getUser(): User? {
        val data = sp.getString(user, null) ?: return null
        return Gson().fromJson<User>(data, User::class.java)
    }

    fun setString(key: String, value: String) {
        return sp.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return sp.getString(key, null) ?: return null
    }
}