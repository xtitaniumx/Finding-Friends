package com.example.peoplefind.domain.repository

import com.example.peoplefind.domain.model.request.SaveUserTokensParam
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    fun getUserTokens(): Pair<Flow<String?>, Flow<String?>>

    suspend fun saveUserTokens(param: SaveUserTokensParam)

    suspend fun deleteUserTokens()
}