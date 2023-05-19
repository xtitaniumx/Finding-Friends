package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.request.SaveTokensParam
import com.example.peoplefind.domain.repository.TokenRepository

class SaveTokensUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(saveUserTokensParam: SaveTokensParam) {
        tokenRepository.saveTokens(param = saveUserTokensParam)
    }
}