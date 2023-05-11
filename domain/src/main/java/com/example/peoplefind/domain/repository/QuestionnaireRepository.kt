package com.example.peoplefind.domain.repository

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.CreateQuestionnaireParam
import kotlinx.coroutines.flow.Flow

interface QuestionnaireRepository {
    fun createQuestionnaire(param: CreateQuestionnaireParam): Flow<ApiResult<Unit>>
}