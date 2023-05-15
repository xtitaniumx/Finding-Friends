package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.CreateQuestionnaireParam
import com.example.peoplefind.domain.repository.QuestionnaireRepository
import kotlinx.coroutines.flow.Flow

class CreateQuestionnaireUseCase(private val questionnaireRepository: QuestionnaireRepository) {
    operator fun invoke(createQuestionnaireParam: CreateQuestionnaireParam): Flow<ApiResult<Unit>> {
        return questionnaireRepository.createQuestionnaire(
            param = createQuestionnaireParam
        )
    }
}