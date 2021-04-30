package com.barenpasaribu.tokoonline.model

class ResponseModel {
    var success: Boolean = false
    lateinit var message: String
    val user: User = User()
    val products: ArrayList<Product> = ArrayList()
}