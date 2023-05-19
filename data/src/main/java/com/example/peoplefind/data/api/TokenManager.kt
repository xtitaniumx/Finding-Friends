package com.example.peoplefind.data.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.peoplefind.domain.model.request.SaveTokensParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token_data")

class TokenManager(private val context: Context) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("jwt_refresh_token")
        private val STREAM_CHAT_TOKEN_KEY = stringPreferencesKey("jwt_stream_chat_token")
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    fun getRefreshToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN_KEY]
        }
    }

    fun getStreamChatToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[STREAM_CHAT_TOKEN_KEY]
        }
    }

    suspend fun saveTokens(saveTokensParam: SaveTokensParam) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = saveTokensParam.accessToken
            preferences[REFRESH_TOKEN_KEY] = saveTokensParam.refreshToken
            preferences[STREAM_CHAT_TOKEN_KEY] = saveTokensParam.streamChatToken
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    suspend fun deleteRefreshToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }

    suspend fun deleteStreamChatToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(STREAM_CHAT_TOKEN_KEY)
        }
    }
}