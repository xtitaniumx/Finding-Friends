package com.example.peoplefind.domain.repository

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.LoginAccountParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.model.response.AuthInfo
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun registerAccount(param: RegisterAccountParam): Flow<ApiResult<AuthInfo>>

    fun loginAccount(param: LoginAccountParam): Flow<ApiResult<AuthInfo>>

    fun getUserId(): Flow<String?>

    fun getUserLoginState(): Flow<Boolean?>

    suspend fun saveUserData(param: SaveLoginDataParam)

    suspend fun deleteAllUserData()
}