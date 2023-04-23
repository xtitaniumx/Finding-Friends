package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

class GetUserTokensUserCase(private val tokenRepository: TokenRepository) {
    operator fun invoke(): Pair<Flow<String?>, Flow<String?>> {
        return tokenRepository.getUserTokens()
    }
}