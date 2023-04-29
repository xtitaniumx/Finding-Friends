package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.repository.UserRepository

class SaveUserDataUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(saveLoginDataParam: SaveLoginDataParam) {
        userRepository.saveUserData(param = saveLoginDataParam)
    }
}