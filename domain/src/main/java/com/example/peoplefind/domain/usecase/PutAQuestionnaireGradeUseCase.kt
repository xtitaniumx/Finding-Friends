package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.PutAQuestionnaireGradeParam
import com.example.peoplefind.domain.repository.QuestionnaireRepository
import kotlinx.coroutines.flow.Flow

class PutAQuestionnaireGradeUseCase(private val questionnaireRepository: QuestionnaireRepository) {
    operator fun invoke(putAQuestionnaireGradeParam: PutAQuestionnaireGradeParam): Flow<ApiResult<Unit>> {
        return questionnaireRepository.putAQuestionnaireGrade(param = putAQuestionnaireGradeParam)
    }
}