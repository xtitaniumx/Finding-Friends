package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.request.SaveUserTokensParam
import com.example.peoplefind.domain.repository.TokenRepository

class SaveUserTokensUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(saveUserTokensParam: SaveUserTokensParam) {
        tokenRepository.saveUserTokens(param = saveUserTokensParam)
    }
}