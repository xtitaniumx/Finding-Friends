package com.example.peoplefind.domain.model.request

data class RegisterAccountParam(
    val email: String,
    val password: String,
    val passwordConfirm: String
)