package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.Grade
import com.example.peoplefind.domain.model.request.PutAQuestionnaireGradeParam
import com.example.peoplefind.domain.model.response.QuestionnaireList
import com.example.peoplefind.domain.usecase.GetRecommendationsUseCase
import com.example.peoplefind.domain.usecase.PutAQuestionnaireGradeUseCase

class HomeViewModel(
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val putAQuestionnaireGradeUseCase: PutAQuestionnaireGradeUseCase
) : BaseViewModel() {

    private val recommendationsMutable = MutableLiveData<ApiResult<List<QuestionnaireList>>>()
    val recommendations: LiveData<ApiResult<List<QuestionnaireList>>> = recommendationsMutable

    private val recommendationsFlowErrorMutable = MutableLiveData<String>()
    val recommendationsFlowError: LiveData<String> = recommendationsFlowErrorMutable

    private val questionnaireRateLikeResultMutable = MutableLiveData<ApiResult<Unit>>()
    val questionnaireRateLikeResult: LiveData<ApiResult<Unit>> = questionnaireRateLikeResultMutable

    private val questionnaireRateDislikeResultMutable = MutableLiveData<ApiResult<Unit>>()
    val questionnaireRateDislikeResult: LiveData<ApiResult<Unit>> = questionnaireRateDislikeResultMutable

    private val questionnaireGradeResultFlowErrorMutable = MutableLiveData<String>()
    val questionnaireGradeResultFlowError: LiveData<String> = questionnaireGradeResultFlowErrorMutable

    fun getRecommendations() = baseRequest(recommendationsMutable, recommendationsFlowErrorMutable) {
        getRecommendationsUseCase()
    }

    fun likeQuestionnaire(id: String) = baseRequest(questionnaireRateLikeResultMutable, questionnaireGradeResultFlowErrorMutable) {
        putAQuestionnaireGradeUseCase(
            PutAQuestionnaireGradeParam(
                questionnaireId = id,
                gradeValue = Grade.Like.value
            )
        )
    }

    fun dislikeQuestionnaire(id: String) = baseRequest(questionnaireRateDislikeResultMutable, questionnaireGradeResultFlowErrorMutable) {
        putAQuestionnaireGradeUseCase(
            PutAQuestionnaireGradeParam(
                questionnaireId = id,
                gradeValue = Grade.Dislike.value
            )
        )
    }
}