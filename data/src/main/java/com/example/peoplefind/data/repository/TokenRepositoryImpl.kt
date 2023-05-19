package com.example.peoplefind.data.repository

import android.content.Context
import com.example.peoplefind.domain.model.request.SaveTokensParam
import com.example.peoplefind.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

class TokenRepositoryImpl(context: Context) : TokenRepository, BaseRepository(context) {
    override fun getAccessToken(): Flow<String?> {
        return tokenManager.getToken()
    }

    override fun getRefreshToken(): Flow<String?> {
        return tokenManager.getRefreshToken()
    }

    override fun getStreamChatToken(): Flow<String?> {
        return tokenManager.getStreamChatToken()
    }

    override suspend fun saveTokens(param: SaveTokensParam) {
        tokenManager.saveTokens(saveTokensParam = param)
    }

    override suspend fun deleteTokens() {
        tokenManager.deleteToken()
        tokenManager.deleteRefreshToken()
        tokenManager.deleteStreamChatToken()
    }
}