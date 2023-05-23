package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.FillQuestionnaireParam
import com.example.peoplefind.domain.repository.QuestionnaireRepository
import kotlinx.coroutines.flow.Flow

class FillQuestionnaireUseCase(private val questionnaireRepository: QuestionnaireRepository) {
    operator fun invoke(fillQuestionnaireParam: FillQuestionnaireParam): Flow<ApiResult<Unit>> {
        return questionnaireRepository.fillQuestionnaire(param = fillQuestionnaireParam)
    }
}