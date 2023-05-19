package com.example.peoplefind.data.api

import com.example.peoplefind.domain.model.request.RefreshAccessTokenParam
import com.example.peoplefind.domain.model.request.SaveTokensParam
import com.example.peoplefind.domain.model.response.AuthInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Call

class AuthAuthenticator(private val tokenManager: TokenManager) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getRefreshToken().first()
        }
        return runBlocking {
            val newToken = getNewToken(token).execute()

            if (!newToken.isSuccessful || newToken.body() == null)
                tokenManager.deleteToken()

            newToken.body()?.let {
                tokenManager.saveTokens(SaveTokensParam(
                    accessToken = it.accessToken,
                    refreshToken = it.refreshToken,
                    streamChatToken = it.streamChatToken
                ))
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }

    private fun getNewToken(refreshToken: String?): Call<AuthInfo> {
        val apiClient = ApiClient().getApiService(tokenManager)
        return apiClient.refreshToken(request = RefreshAccessTokenParam("Bearer $refreshToken"))
    }
}