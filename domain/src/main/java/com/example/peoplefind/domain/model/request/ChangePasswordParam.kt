package com.example.peoplefind.domain.model.request

data class ChangePasswordParam(
    val email: String,
    val oldPassword: String,
    val newPassword: String
)
