package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.response.QuestionnaireList
import com.example.peoplefind.domain.repository.QuestionnaireRepository
import kotlinx.coroutines.flow.Flow

class GetRecommendationsUseCase(private val questionnaireRepository: QuestionnaireRepository) {
    operator fun invoke(): Flow<ApiResult<List<QuestionnaireList>>> {
        return questionnaireRepository.getRecommendations()
    }
}