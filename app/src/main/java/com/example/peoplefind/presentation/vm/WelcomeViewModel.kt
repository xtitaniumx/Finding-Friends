package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peoplefind.domain.usecase.GetUserLoginStateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WelcomeViewModel(private val getUserLoginStateUseCase: GetUserLoginStateUseCase) : ViewModel() {
    private val loginStateMutable = MutableLiveData<Boolean?>()
    val loginState: LiveData<Boolean?> = loginStateMutable

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserLoginStateUseCase().collect { state ->
                loginStateMutable.postValue(state)
            }
        }
    }
}