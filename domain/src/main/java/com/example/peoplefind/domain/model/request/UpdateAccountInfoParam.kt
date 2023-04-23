package com.example.peoplefind.domain.model.request

import com.example.peoplefind.domain.model.Address

data class UpdateAccountInfoParam(
    val name: String,
    val surname: String,
    val birthDate: String,
    val address: Address
)
