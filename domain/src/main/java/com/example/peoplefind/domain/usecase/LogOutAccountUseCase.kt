package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class LogOutAccountUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<ApiResult<Unit>> {
        return userRepository.logoutAccount()
    }
}