package com.example.peoplefind.domain.model.response

import com.example.peoplefind.domain.model.Address

data class User(
    val id: String,
    val email: String,
    val phoneNumber: String,
    val name: String,
    val surname: String,
    val birthDate: String,
    val address: Address
)