package com.example.peoplefind.domain.model.request

data class RegisterAccountParam(
    val email: String,
    val birthDate: String,
    val password: String,
    val passwordConfirm: String
)