package com.example.peoplefind.domain.model.response

import com.example.peoplefind.domain.model.Interest
import com.example.peoplefind.domain.model.QuestionnaireAddress

data class Questionnaire(
    val id: String,
    val name: String,
    val surname: String,
    val birthDate: String,
    val address: QuestionnaireAddress,
    val interests: List<Interest>,
    val userId: String,
    val likes: Int,
    val dislikes: Int,
    val views: Int,
    val isPublished: Boolean
)