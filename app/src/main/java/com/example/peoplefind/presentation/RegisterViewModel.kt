package com.example.peoplefind.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.Role
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.response.AuthInfo
import com.example.peoplefind.domain.usecase.RegisterAccountUseCase

class RegisterViewModel(private val registerAccountUseCase: RegisterAccountUseCase) : BaseViewModel() {
    private val authInfoFlowErrorMutable = MutableLiveData<String>()
    val authInfoFlowError: LiveData<String> = authInfoFlowErrorMutable

    private val authInfoMutable = MutableLiveData<ApiResult<AuthInfo>>()
    val authInfo: LiveData<ApiResult<AuthInfo>> = authInfoMutable

    fun registerAccount(
        email: String,
        birthdate: String,
        password: String,
        passwordConfirm: String
    ) {
        if (email.isEmpty() || birthdate.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            authInfoMutable.value = ApiResult.Failure(
                message = "Одно или несколько обязательных полей не были заполнены",
                error = "HTTP 400",
                code = 400
            )
            return
        }

        baseRequest(authInfoMutable, authInfoFlowErrorMutable) {
            registerAccountUseCase(
                RegisterAccountParam(
                    email, convertDateToServerFormat(birthdate), password, passwordConfirm
                )
            )
        }
    }

    private fun convertDateToServerFormat(date: String): String {
        val newDate = date.replace('/', '-')
        return "${newDate}T00:00:00.000Z"
    }
}