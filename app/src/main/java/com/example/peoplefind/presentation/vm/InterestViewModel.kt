package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.peoplefind.domain.model.Interest

class InterestViewModel : ViewModel() {
    private val interestMutable = MutableLiveData<Interest>()
    val interest: LiveData<Interest> = interestMutable

    fun setInterest(name: String, description: String) {
        interestMutable.value = Interest(name, description)
    }
}