package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.usecase.DeleteUserDataUseCase
import com.example.peoplefind.domain.usecase.LogOutAccountUseCase
import io.getstream.chat.android.client.ChatClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserDataUseCase()
            ChatClient.instance().disconnect(flushPersistence = true).execute()
        }
    }
}