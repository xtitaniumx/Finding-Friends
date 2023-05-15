package com.example.peoplefind.domain.model.request

data class PutAQuestionnaireGradeParam(
    val questionnaireId: String,
    val gradeValue: Int
)