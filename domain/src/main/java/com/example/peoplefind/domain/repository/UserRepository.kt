package com.example.peoplefind.domain.repository

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.FetchUserDataParam
import com.example.peoplefind.domain.model.request.LoginAccountParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.model.response.AuthInfo
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    companion object {
        const val USER_ID = "user_id"
        const val USER_LOGGED_IN = "user_logged_in"
    }

    fun registerAccount(param: RegisterAccountParam): Flow<ApiResult<AuthInfo>>

    fun loginAccount(param: LoginAccountParam): Flow<ApiResult<AuthInfo>>

    fun saveLoginData(param: SaveLoginDataParam)

    fun fetchUserData(param: FetchUserDataParam): String?

    fun removeAllUserData()
}