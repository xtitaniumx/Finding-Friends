package com.example.peoplefind.domain.repository

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.FillQuestionnaireParam
import com.example.peoplefind.domain.model.request.GetQuestionnaireParam
import com.example.peoplefind.domain.model.request.PutAQuestionnaireGradeParam
import com.example.peoplefind.domain.model.request.UpdateQuestionnaireParam
import com.example.peoplefind.domain.model.response.Questionnaire
import com.example.peoplefind.domain.model.response.QuestionnaireList
import kotlinx.coroutines.flow.Flow

interface QuestionnaireRepository {
    fun getRecommendations(): Flow<ApiResult<List<QuestionnaireList>>>

    fun getQuestionnaire(param: GetQuestionnaireParam): Flow<ApiResult<Questionnaire>>

    fun fillQuestionnaire(param: FillQuestionnaireParam): Flow<ApiResult<Unit>>

    fun putAQuestionnaireGrade(param: PutAQuestionnaireGradeParam): Flow<ApiResult<Unit>>

    fun updateQuestionnaire(param: UpdateQuestionnaireParam): Flow<ApiResult<Unit>>
}