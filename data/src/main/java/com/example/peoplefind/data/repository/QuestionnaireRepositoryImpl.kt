package com.example.peoplefind.data.repository

import android.content.Context
import com.example.peoplefind.data.api.ApiClient
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.FillQuestionnaireParam
import com.example.peoplefind.domain.model.request.UpdateQuestionnaireParam
import com.example.peoplefind.domain.model.response.Questionnaire
import com.example.peoplefind.domain.repository.QuestionnaireRepository
import kotlinx.coroutines.flow.Flow

class QuestionnaireRepositoryImpl(apiClient: ApiClient, context: Context) : QuestionnaireRepository, BaseRepository(context) {
    private val apiService = apiClient.getApiService(tokenManager)

    override fun getRecommendations(): Flow<ApiResult<List<Questionnaire>>> = apiRequestFlow {
        apiService.getRecommendations()
    }

    override fun fillQuestionnaire(param: FillQuestionnaireParam): Flow<ApiResult<Unit>> = apiRequestFlow {
        apiService.fillQuestionnaire(request = param)
    }

    override fun updateQuestionnaire(param: UpdateQuestionnaireParam): Flow<ApiResult<Unit>> = apiRequestFlow {
        apiService.updateQuestionnaire(request = param)
    }
}