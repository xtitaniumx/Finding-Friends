package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InterestViewModel : ViewModel() {
    private val interestMutable = MutableLiveData<Pair<Int, String>>()
    val interest: LiveData<Pair<Int, String>> = interestMutable

    fun setInterest(id: Int, description: String) {
        interestMutable.value = Pair(id, description)
    }
}