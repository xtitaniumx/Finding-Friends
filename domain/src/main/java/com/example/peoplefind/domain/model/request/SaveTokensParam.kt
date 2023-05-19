package com.example.peoplefind.domain.model.request

data class SaveTokensParam(
    val accessToken: String,
    val refreshToken: String,
    val streamChatToken: String
)