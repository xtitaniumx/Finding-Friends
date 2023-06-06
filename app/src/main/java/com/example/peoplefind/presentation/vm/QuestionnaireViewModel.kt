package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.Interest
import com.example.peoplefind.domain.model.QuestionnaireAddress
import com.example.peoplefind.domain.model.request.FillQuestionnaireParam
import com.example.peoplefind.domain.model.request.GetQuestionnaireParam
import com.example.peoplefind.domain.model.request.UpdateQuestionnaireParam
import com.example.peoplefind.domain.model.response.Questionnaire
import com.example.peoplefind.domain.usecase.FillQuestionnaireUseCase
import com.example.peoplefind.domain.usecase.GetQuestionnaireUseCase
import com.example.peoplefind.domain.usecase.UpdateQuestionnaireUseCase

class QuestionnaireViewModel(
    private val getQuestionnaireUseCase: GetQuestionnaireUseCase,
    private val fillQuestionnaireUseCase: FillQuestionnaireUseCase,
    private val updateQuestionnaireUseCase: UpdateQuestionnaireUseCase
) : BaseViewModel() {
    private val questionnaireMutable = MutableLiveData<ApiResult<Questionnaire>>()
    val questionnaire: LiveData<ApiResult<Questionnaire>> = questionnaireMutable

    private val questionnaireFlowMutable = MutableLiveData<String>()
    val questionnaireFlow: LiveData<String> = questionnaireFlowMutable

    private val fillQuestionnaireResultFlowMutable = MutableLiveData<String>()
    val fillQuestionnaireResultFlow: LiveData<String> = fillQuestionnaireResultFlowMutable

    private val fillQuestionnaireResultMutable = MutableLiveData<ApiResult<Unit>>()
    val fillQuestionnaireResult: LiveData<ApiResult<Unit>> = fillQuestionnaireResultMutable

    private val updateQuestionnaireResultFlowMutable = MutableLiveData<String>()
    val updateQuestionnaireResultFlow: LiveData<String> = updateQuestionnaireResultFlowMutable

    private val updateQuestionnaireResultMutable = MutableLiveData<ApiResult<Unit>>()
    val updateQuestionnaireResult: LiveData<ApiResult<Unit>> = updateQuestionnaireResultMutable

    private val interestsMutable = MutableLiveData<List<Interest>>()
    val interests: LiveData<List<Interest>> = interestsMutable

    fun getQuestionnaire(userId: String) = baseRequest(questionnaireMutable, questionnaireFlowMutable) {
        getQuestionnaireUseCase(
            GetQuestionnaireParam(
                userId = userId
            )
        )
    }

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

    fun addInterest(name: String, description: String) {
        val list = ArrayList<Interest>()
        interestsMutable.value?.let { list.addAll(it) }
        list.add(Interest(name, description))
        interestsMutable.value = list
    }

    fun removeInterest(name: String) {
        val list = ArrayList<Interest>()
        interestsMutable.value?.let { list.addAll(it) }
        list.removeAll { it.name == name }
        interestsMutable.value = list
    }
}