package com.example.peoplefind.data.repository

import android.content.Context
import com.example.peoplefind.data.api.ApiClient
import com.example.peoplefind.domain.model.request.FetchUserDataParam
import com.example.peoplefind.domain.model.request.LoginAccountParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.repository.UserRepository

class UserRepositoryImpl(apiClient: ApiClient, context: Context) : UserRepository, BaseRepository(context) {
    private val apiService = apiClient.getApiService(tokenManager)
    private val pref = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    override fun registerAccount(param: RegisterAccountParam) = apiRequestFlow {
        apiService.registerUser(request = param)
    }

    override fun loginAccount(param: LoginAccountParam) = apiRequestFlow {
        apiService.loginUser(request = param)
    }

    override fun saveLoginData(param: SaveLoginDataParam) {
        saveUserData(param.userId, UserRepository.USER_ID)
        saveUserData(param.rememberState.toString(), UserRepository.USER_LOGGED_IN)
    }

    override fun fetchUserData(param: FetchUserDataParam): String? {
        return pref.getString(param.dataId, null)
    }

    override fun removeAllUserData() {
        pref.edit().clear().apply()
    }

    private fun saveUserData(data: String, dataId: String) {
        pref.edit().apply {
            putString(dataId, data)
            apply()
        }
    }
}