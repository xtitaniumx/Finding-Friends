package com.example.peoplefind.domain.repository

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.FillQuestionnaireParam
import com.example.peoplefind.domain.model.request.UpdateQuestionnaireParam
import kotlinx.coroutines.flow.Flow

interface QuestionnaireRepository {
    fun fillQuestionnaire(param: FillQuestionnaireParam): Flow<ApiResult<Unit>>

    fun updateQuestionnaire(param: UpdateQuestionnaireParam): Flow<ApiResult<Unit>>
}