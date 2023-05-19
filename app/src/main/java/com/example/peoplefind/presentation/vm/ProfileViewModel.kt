package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.usecase.DeleteUserDataUseCase
import com.example.peoplefind.domain.usecase.LogOutAccountUseCase
import kotlinx.coroutines.runBlocking

class ProfileViewModel(
    private val logoutAccountUseCase: LogOutAccountUseCase,
    private val deleteUserDataUseCase: DeleteUserDataUseCase
) : BaseViewModel() {
    private val logoutResultFlowErrorMutable = MutableLiveData<String>()
    val logoutResultFlowError: LiveData<String> = logoutResultFlowErrorMutable

    private val logoutResultMutable = MutableLiveData<ApiResult<Unit>>()
    val logoutResult: LiveData<ApiResult<Unit>> = logoutResultMutable

    fun logoutAccount() = baseRequest(logoutResultMutable, logoutResultFlowErrorMutable) {
        logoutAccountUseCase()
    }

    fun deleteUserData() {
        runBlocking {
            deleteUserDataUseCase()
        }
    }
}