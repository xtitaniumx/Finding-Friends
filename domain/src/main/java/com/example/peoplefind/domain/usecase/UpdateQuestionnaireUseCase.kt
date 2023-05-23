package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.UpdateQuestionnaireParam
import com.example.peoplefind.domain.repository.QuestionnaireRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateQuestionnaireUseCase(private val questionnaireRepository: QuestionnaireRepository) {
    operator fun invoke(updateQuestionnaireParam: UpdateQuestionnaireParam): Flow<ApiResult<Unit>> {
        if (updateQuestionnaireParam.name.isEmpty() ||
            updateQuestionnaireParam.surname.isEmpty() ||
            updateQuestionnaireParam.birthDate.isEmpty()
        ) {
            return flow {
                emit(
                    ApiResult.Failure(
                        message = "Одно или несколько обязательных полей не были заполнены",
                        error = "HTTP 400"
                    )
                )
            }
        }

        return questionnaireRepository.updateQuestionnaire(param = updateQuestionnaireParam)
    }
}