package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.LoginAccountParam
import com.example.peoplefind.domain.model.response.AuthInfo
import com.example.peoplefind.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class LoginAccountUseCase(private val userRepository: UserRepository) {
    operator fun invoke(loginAccountParam: LoginAccountParam): Flow<ApiResult<AuthInfo>> {
        return userRepository.loginAccount(param = loginAccountParam)
    }
}