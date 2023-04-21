package com.example.peoplefind.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peoplefind.domain.model.onError
import com.example.peoplefind.domain.model.onSuccess
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.response.UserItem
import com.example.peoplefind.domain.usecase.RegisterAccountUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val registerAccountUseCase: RegisterAccountUseCase) : ViewModel() {
    private val userMutable = MutableLiveData<UserItem>()
    val user: LiveData<UserItem> = userMutable

    fun getUserData() {
        viewModelScope.launch {
            val response = registerAccountUseCase(RegisterAccountParam("", "", "", ""))
            response.onSuccess {
                userMutable.postValue(it)
            }.onError { e, message ->

            }
        }
    }
}