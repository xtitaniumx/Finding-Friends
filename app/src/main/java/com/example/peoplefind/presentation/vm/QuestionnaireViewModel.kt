package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.Interest
import com.example.peoplefind.domain.model.QuestionnaireAddress
import com.example.peoplefind.domain.model.request.FillQuestionnaireParam
import com.example.peoplefind.domain.usecase.FillQuestionnaireUseCase

class QuestionnaireViewModel(
    private val fillQuestionnaireUseCase: FillQuestionnaireUseCase
) : BaseViewModel() {
    private val fillQuestionnaireResultFlowMutable = MutableLiveData<String>()
    val fillQuestionnaireResultFlow: LiveData<String> = fillQuestionnaireResultFlowMutable

    private val fillQuestionnaireResultMutable = MutableLiveData<ApiResult<Unit>>()
    val fillQuestionnaireResult: LiveData<ApiResult<Unit>> = fillQuestionnaireResultMutable

    private val interestsDescriptionListMutable = MutableLiveData<List<String>>()
    val interestsDescriptionList: LiveData<List<String>> = interestsDescriptionListMutable

    fun fillQuestionnaire(
        name: String,
        surname: String,
        birthDate: String,
        address: QuestionnaireAddress,
        interests: List<Interest>
    ) = baseRequest(fillQuestionnaireResultMutable, fillQuestionnaireResultFlowMutable) {
        fillQuestionnaireUseCase(
            FillQuestionnaireParam(name, surname, birthDate, address, interests)
        )
    }

    fun addInterestDescription(number: Int, description: String) {
        val list = ArrayList<String>()
        interestsDescriptionListMutable.value?.let { list.addAll(it) }
        list.add(number, description)
        interestsDescriptionListMutable.value = list
    }

    fun removeInterestDescription(number: Int) {
        val list = ArrayList<String>()
        interestsDescriptionListMutable.value?.let { list.addAll(it) }
        list[number] = ""
        interestsDescriptionListMutable.value = list
    }
}