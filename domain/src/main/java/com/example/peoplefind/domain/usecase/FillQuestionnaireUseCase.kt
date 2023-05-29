package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.FillQuestionnaireParam
import com.example.peoplefind.domain.repository.QuestionnaireRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FillQuestionnaireUseCase(private val questionnaireRepository: QuestionnaireRepository) {
    operator fun invoke(fillQuestionnaireParam: FillQuestionnaireParam): Flow<ApiResult<Unit>> {
        if (fillQuestionnaireParam.name.isEmpty() || fillQuestionnaireParam.birthDate.isEmpty()) {
            return flow {
                emit(
                    ApiResult.Failure(
                        message = "Необходимо заполнить поля отмеченные звездочкой",
                        error = "HTTP 400"
                    )
                )
            }
        }

        return questionnaireRepository.fillQuestionnaire(param = fillQuestionnaireParam)
    }
}