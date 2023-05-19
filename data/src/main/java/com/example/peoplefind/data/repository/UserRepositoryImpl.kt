package com.example.peoplefind.data.repository

import android.content.Context
import com.example.peoplefind.data.api.ApiClient
import com.example.peoplefind.data.api.UserDataManager
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.LoginAccountParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(apiClient: ApiClient, context: Context) : UserRepository, BaseRepository(context) {
    private val apiService = apiClient.getApiService(tokenManager)
    private val userDataManager = UserDataManager(context)

    override fun registerAccount(param: RegisterAccountParam) = apiRequestFlow {
        apiService.registerUser(request = param)
    }

    override fun loginAccount(param: LoginAccountParam) = apiRequestFlow {
        apiService.loginUser(request = param)
    }

    override fun logoutAccount(): Flow<ApiResult<Unit>> = apiRequestFlow {
        apiService.logoutUser()
    }

    override fun getUserId(): Flow<String?> {
        return userDataManager.getId()
    }

    override fun getUserLoginState(): Flow<Boolean?> {
        return userDataManager.getLoginState()
    }

    override suspend fun saveUserData(param: SaveLoginDataParam) {
        userDataManager.saveData(param)
    }

    override suspend fun deleteAllUserData() {
        userDataManager.deleteAllData()
    }
}