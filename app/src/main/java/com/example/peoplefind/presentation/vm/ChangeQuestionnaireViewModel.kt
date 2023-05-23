package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.Interest
import com.example.peoplefind.domain.model.QuestionnaireAddress
import com.example.peoplefind.domain.model.request.UpdateQuestionnaireParam
import com.example.peoplefind.domain.usecase.UpdateQuestionnaireUseCase

class ChangeQuestionnaireViewModel(
    private val updateQuestionnaireUseCase: UpdateQuestionnaireUseCase
) : BaseViewModel() {
    private val updateQuestionnaireResultFlowMutable = MutableLiveData<String>()
    val updateQuestionnaireResultFlow: LiveData<String> = updateQuestionnaireResultFlowMutable

    private val updateQuestionnaireResultMutable = MutableLiveData<ApiResult<Unit>>()
    val updateQuestionnaireResult: LiveData<ApiResult<Unit>> = updateQuestionnaireResultMutable

    private val interestsDescriptionListMutable = MutableLiveData<List<String>>()
    val interestsDescriptionList: LiveData<List<String>> = interestsDescriptionListMutable

    fun updateQuestionnaire(
        name: String,
        surname: String,
        birthDate: String,
        address: QuestionnaireAddress,
        interests: List<Interest>
    ) = baseRequest(updateQuestionnaireResultMutable, updateQuestionnaireResultFlowMutable) {
        updateQuestionnaireUseCase(
            UpdateQuestionnaireParam(name, surname, birthDate, address, interests)
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