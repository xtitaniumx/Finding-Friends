package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.GetQuestionnaireParam
import com.example.peoplefind.domain.model.response.Questionnaire
import com.example.peoplefind.domain.repository.QuestionnaireRepository
import kotlinx.coroutines.flow.Flow

class GetQuestionnaireUseCase(private val questionnaireRepository: QuestionnaireRepository) {
    operator fun invoke(getQuestionnaireParam: GetQuestionnaireParam): Flow<ApiResult<Questionnaire>> {
        return questionnaireRepository.getQuestionnaire(param = getQuestionnaireParam)
    }
}