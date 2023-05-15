package com.example.peoplefind.domain.model.response

import com.example.peoplefind.domain.model.QuestionnaireAddress

data class Questionnaire(
    val name: String,
    val surname: String,
    val birthDate: String,
    val address: QuestionnaireAddress
)