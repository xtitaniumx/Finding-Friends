package com.example.peoplefind.domain.model.request

data class LoginAccountParam(
    val phoneNumber: String,
    val password: String,
    val remember: Boolean
)