package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.response.AuthInfo
import com.example.peoplefind.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class RegisterAccountUseCase(private val userRepository: UserRepository) {
    operator fun invoke(registerAccountParam: RegisterAccountParam): Flow<ApiResult<AuthInfo>> {
        return userRepository.registerAccount(param = registerAccountParam)
    }
}