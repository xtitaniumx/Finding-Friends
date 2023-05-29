package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.response.Questionnaire
import com.example.peoplefind.domain.usecase.GetRecommendationsUseCase

class HomeViewModel(private val getRecommendationsUseCase: GetRecommendationsUseCase) : BaseViewModel() {
    private val recommendationsMutable = MutableLiveData<ApiResult<List<Questionnaire>>>()
    val recommendations: LiveData<ApiResult<List<Questionnaire>>> = recommendationsMutable

    private val recommendationsFlowErrorMutable = MutableLiveData<String>()
    val recommendationsFlowError: LiveData<String> = recommendationsFlowErrorMutable

    fun getRecommendations() = baseRequest(recommendationsMutable, recommendationsFlowErrorMutable) {
        getRecommendationsUseCase()
    }
}