package com.example.peoplefind.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.Role
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.response.AuthInfo
import com.example.peoplefind.domain.usecase.RegisterAccountUseCase

class MainViewModel(private val registerAccountUseCase: RegisterAccountUseCase) : BaseViewModel() {
    private val authInfoMutable = MutableLiveData<ApiResult<AuthInfo>>()
    val authInfo: LiveData<ApiResult<AuthInfo>> = authInfoMutable

    fun registerAccount(
        coroutinesErrorHandler: CoroutinesErrorHandler,
        email: String,
        birthDate: String,
        password: String,
        passwordConfirm: String,
        role: Role
    ) = baseRequest(authInfoMutable, coroutinesErrorHandler) {
        registerAccountUseCase(
            RegisterAccountParam(email, birthDate, password, passwordConfirm, role)
        )
    }
}