package com.barenpasaribu.tokoonline.model

data class ResponseUser(
    val message: String,
    val success: Boolean,
    val user: User
) {
    data class User(
        val avatar: String,
        val created_at: String,
        val email: String,
        val email_verified_at: Any,
        val id: Int,
        val name: String,
        val phone: String,
        val updated_at: String
    )
}