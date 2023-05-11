package com.example.peoplefind.domain.model.request

import com.example.peoplefind.domain.model.QuestionnaireAddress

data class UpdateQuestionnaireParam(
    val name: String,
    val surname: String,
    val birthDate: String,
    val address: QuestionnaireAddress
)