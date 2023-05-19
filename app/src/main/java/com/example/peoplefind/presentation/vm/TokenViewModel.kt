package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peoplefind.domain.model.request.SaveTokensParam
import com.example.peoplefind.domain.usecase.DeleteUserTokensUseCase
import com.example.peoplefind.domain.usecase.GetTokenUseCase
import com.example.peoplefind.domain.usecase.SaveTokensUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TokenViewModel(
    private val getAccessTokenUseCase: GetTokenUseCase.GetAccessToken,
    private val getRefreshTokenUseCase: GetTokenUseCase.GetRefreshToken,
    private val getStreamChatTokenUseCase: GetTokenUseCase.GetStreamChatToken,
    private val saveUserTokensUseCase: SaveTokensUseCase,
    private val deleteUserTokensUseCase: DeleteUserTokensUseCase
) : ViewModel() {
    private val tokenMutable = MutableLiveData<String?>()
    val token: LiveData<String?> = tokenMutable

    private val refreshTokenMutable = MutableLiveData<String?>()
    val refreshToken: LiveData<String?> = refreshTokenMutable

    private val streamChatTokenMutable = MutableLiveData<String?>()
    val streamChatToken: LiveData<String?> = streamChatTokenMutable

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAccessTokenUseCase.invoke().collect {
                withContext(Dispatchers.Main) {
                    tokenMutable.value = it
                }
            }
            getRefreshTokenUseCase.invoke().collect {
                withContext(Dispatchers.Main) {
                    refreshTokenMutable.value = it
                }
            }
            getStreamChatTokenUseCase.invoke().collect {
                withContext(Dispatchers.Main) {
                    streamChatTokenMutable.value = it
                }
            }
        }
    }

    fun saveToken(token: String, refreshToken: String, streamChatToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserTokensUseCase(SaveTokensParam(token, refreshToken, streamChatToken))
        }
    }

    fun deleteToken() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserTokensUseCase()
        }
    }
}