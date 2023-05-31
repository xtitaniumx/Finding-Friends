package com.example.peoplefind.domain.model.response

import com.example.peoplefind.domain.model.QuestionnaireAddress

data class QuestionnaireList(
    val id: String,
    val name: String,
    val surname: String,
    val birthDate: String,
    val address: QuestionnaireAddress,
)