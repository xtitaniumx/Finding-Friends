package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.usecase.DeleteUserDataUseCase
import com.example.peoplefind.domain.usecase.GetUserIdUseCase
import com.example.peoplefind.domain.usecase.GetUserLoginStateUseCase
import com.example.peoplefind.domain.usecase.SaveUserDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDataViewModel(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getUserLoginStateUseCase: GetUserLoginStateUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase,
    private val deleteUserDataUseCase: DeleteUserDataUseCase
) : ViewModel() {
    private val userIdMutable = MutableLiveData<String?>()
    val userId: LiveData<String?> = userIdMutable

    private val userLoginStateMutable = MutableLiveData<Boolean?>()
    val userLoginState: LiveData<Boolean?> = userLoginStateMutable

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = getUserIdUseCase()
            userId.collect {
                withContext(Dispatchers.Main) {
                    userIdMutable.value = it
                }
            }
            val userLoginState = getUserLoginStateUseCase()
            userLoginState.collect {
                withContext(Dispatchers.Main) {
                    userLoginStateMutable.value = it
                }
            }
        }
    }

    fun saveUserData(userId: String, loginState: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserDataUseCase(SaveLoginDataParam(userId, loginState))
        }
    }

    fun deleteUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserDataUseCase()
        }
    }
}