package com.example.peoplefind.domain.model.request

import com.example.peoplefind.domain.model.Interest
import com.example.peoplefind.domain.model.QuestionnaireAddress

data class FillQuestionnaireParam(
    val name: String,
    val surname: String,
    val birthDate: String,
    val address: QuestionnaireAddress,
    val interests: List<Interest>
)