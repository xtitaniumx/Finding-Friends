package com.example.peoplefind.domain.model.request

data class SaveLoginDataParam(
    val userId: String,
    val rememberState: Boolean
)