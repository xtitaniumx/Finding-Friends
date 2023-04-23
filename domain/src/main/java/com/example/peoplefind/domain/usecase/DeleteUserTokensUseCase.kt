package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.repository.TokenRepository

class DeleteUserTokensUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke() {
        tokenRepository.deleteUserTokens()
    }
}