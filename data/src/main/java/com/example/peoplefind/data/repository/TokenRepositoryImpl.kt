package com.example.peoplefind.data.repository

import android.content.Context
import com.example.peoplefind.domain.model.request.SaveUserTokensParam
import com.example.peoplefind.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

class TokenRepositoryImpl(context: Context) : TokenRepository, BaseRepository(context) {
    override fun getUserTokens(): Pair<Flow<String?>, Flow<String?>> {
        val token = tokenManager.getToken()
        val refreshToken = tokenManager.getRefreshToken()
        return Pair(token, refreshToken)
    }

    override suspend fun saveUserTokens(param: SaveUserTokensParam) {
        tokenManager.saveRefreshToken(param.refreshToken)
        tokenManager.saveToken(param.accessToken)
    }

    override suspend fun deleteUserTokens() {
        tokenManager.deleteToken()
        tokenManager.deleteRefreshToken()
    }
}