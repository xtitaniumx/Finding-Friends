package com.example.peoplefind.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peoplefind.domain.model.request.SaveUserTokensParam
import com.example.peoplefind.domain.usecase.DeleteUserTokensUseCase
import com.example.peoplefind.domain.usecase.GetUserTokensUserCase
import com.example.peoplefind.domain.usecase.SaveUserTokensUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TokenViewModel(
    private val getUserTokensUseCase: GetUserTokensUserCase,
    private val saveUserTokensUseCase: SaveUserTokensUseCase,
    private val deleteUserTokensUseCase: DeleteUserTokensUseCase
) : ViewModel() {
    private val tokenMutable = MutableLiveData<String?>()
    val token: LiveData<String?> = tokenMutable

    private val refreshTokenMutable = MutableLiveData<String?>()
    val refreshToken: LiveData<String?> = refreshTokenMutable

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val tokens = getUserTokensUseCase()
            tokens.first.collect {
                withContext(Dispatchers.Main) {
                    tokenMutable.value = it
                }
            }
            tokens.second.collect {
                withContext(Dispatchers.Main) {
                    refreshTokenMutable.value = it
                }
            }
        }
    }

    fun saveToken(token: String, refreshToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserTokensUseCase(SaveUserTokensParam(token, refreshToken))
        }
    }

    fun deleteToken() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserTokensUseCase()
        }
    }
}