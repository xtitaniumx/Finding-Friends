package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserIdUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<String?> {
        return userRepository.getUserId()
    }
}