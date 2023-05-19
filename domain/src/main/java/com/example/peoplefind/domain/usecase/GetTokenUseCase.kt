package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

sealed class GetTokenUseCase {
    class GetAccessToken(private val tokenRepository: TokenRepository) : GetTokenUseCase() {
        operator fun invoke(): Flow<String?> = tokenRepository.getAccessToken()
    }

    class GetRefreshToken(private val tokenRepository: TokenRepository) : GetTokenUseCase() {
        operator fun invoke(): Flow<String?> = tokenRepository.getRefreshToken()
    }

    class GetStreamChatToken(private val tokenRepository: TokenRepository) : GetTokenUseCase() {
        operator fun invoke(): Flow<String?> = tokenRepository.getStreamChatToken()
    }
}