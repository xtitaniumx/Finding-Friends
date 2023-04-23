package com.example.peoplefind.domain.model.request

data class SaveUserTokensParam(
    val accessToken: String,
    val refreshToken: String
)
