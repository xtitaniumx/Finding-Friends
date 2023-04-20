package com.example.peoplefind.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.peoplefind.domain.model.response.UserItem

class MainViewModel : ViewModel() {
    private val userMutable = MutableLiveData<UserItem>()
    val user: LiveData<UserItem> = userMutable

    fun getUserData() {

    }
}