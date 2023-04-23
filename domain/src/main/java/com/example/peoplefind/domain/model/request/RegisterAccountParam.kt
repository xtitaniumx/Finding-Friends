package com.example.peoplefind.domain.model.request

import com.example.peoplefind.domain.model.Role

data class RegisterAccountParam(
    val email: String,
    val birthDate: String,
    val password: String,
    val passwordConfirm: String,
    val role: Role
)