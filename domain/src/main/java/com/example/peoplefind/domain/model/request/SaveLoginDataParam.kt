package com.example.peoplefind.domain.model.request

import com.example.peoplefind.domain.model.response.UserItem

data class SaveLoginDataParam(
    val login: String,
    val password: String,
    val rememberState: Boolean,
    val userItem: UserItem
)