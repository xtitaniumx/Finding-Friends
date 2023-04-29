package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.repository.UserRepository

class DeleteUserDataUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke() {
        userRepository.deleteAllUserData()
    }
}