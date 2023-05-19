package com.example.peoplefind.domain.repository

import com.example.peoplefind.domain.model.request.SaveTokensParam
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    fun getAccessToken(): Flow<String?>

    fun getRefreshToken(): Flow<String?>

    fun getStreamChatToken(): Flow<String?>

    suspend fun saveTokens(param: SaveTokensParam)

    suspend fun deleteTokens()
}