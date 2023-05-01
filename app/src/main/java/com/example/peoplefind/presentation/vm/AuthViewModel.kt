package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.LoginAccountParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.response.AuthInfo
import com.example.peoplefind.domain.usecase.LoginAccountUseCase
import com.example.peoplefind.domain.usecase.RegisterAccountUseCase

class AuthViewModel(
    private val registerAccountUseCase: RegisterAccountUseCase,
    private val loginAccountUseCase: LoginAccountUseCase
) : BaseViewModel() {
    private val authInfoFlowErrorMutable = MutableLiveData<String>()
    val authInfoFlowError: LiveData<String> = authInfoFlowErrorMutable

    private val authInfoMutable = MutableLiveData<ApiResult<AuthInfo>>()
    val authInfo: LiveData<ApiResult<AuthInfo>> = authInfoMutable

    fun registerAccount(
        email: String,
        birthdate: String,
        password: String,
        passwordConfirm: String
    ) = baseRequest(authInfoMutable, authInfoFlowErrorMutable) {
        registerAccountUseCase(
            RegisterAccountParam(
                email, convertDateToServerFormat(birthdate), password, passwordConfirm
            )
        )
    }

    fun loginAccount(email: String, password: String) =
        baseRequest(authInfoMutable, authInfoFlowErrorMutable) {
            loginAccountUseCase(
                LoginAccountParam(
                    email, password
                )
            )
        }

    private fun convertDateToServerFormat(date: String): String {
        val newDate = date.replace('/', '-')
        return "${newDate}T00:00:00.000Z"
    }
}