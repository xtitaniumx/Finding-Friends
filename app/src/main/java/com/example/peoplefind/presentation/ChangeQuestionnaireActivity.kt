package com.example.peoplefind.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityChangeQuestionnaireBinding
import com.example.peoplefind.domain.extension.onFailure
import com.example.peoplefind.domain.extension.onLoading
import com.example.peoplefind.domain.extension.onSuccess
import com.example.peoplefind.domain.model.QuestionnaireAddress
import com.example.peoplefind.presentation.util.convertAgeToIsoDateTime
import com.example.peoplefind.presentation.vm.InterestViewModel
import com.example.peoplefind.presentation.vm.QuestionnaireViewModel
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeQuestionnaireActivity : AppCompatActivity() {
    private val questionnaireViewModel by viewModel<QuestionnaireViewModel>()
    private val interestViewModel by viewModels<InterestViewModel>()
    private lateinit var binding: ActivityChangeQuestionnaireBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeQuestionnaireBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        val interests = resources.getStringArray(R.array.interests)
        interests.forEach {
            addChip(interests.indexOf(it), it)
        }

        interestViewModel.interest.observe(this@ChangeQuestionnaireActivity) {
            questionnaireViewModel.addInterest(name = it.name, description = it.description)
        }

        buttonConfirmChangeQuestionnaire.setOnClickListener {
            questionnaireViewModel.updateQuestionnaire(
                name = editTextName.text.toString(),
                surname = "",
                birthDate = convertAgeToIsoDateTime(editTextAge.text.toString()),
                address = QuestionnaireAddress(
                    region = "",
                    city = editTextCity.text.toString(),
                    street = "",
                    numberOfHome = ""
                ),
                interests = questionnaireViewModel.interests.value!!
            )
        }

        questionnaireViewModel.updateQuestionnaireResult.observe(this@ChangeQuestionnaireActivity) { result ->
            result.onLoading {
                skeletonConfirmChangeQuestionnaire.showSkeleton()
            }.onSuccess {
                skeletonConfirmChangeQuestionnaire.showOriginal()
                finish()
            }.onFailure { _, _ ->
                skeletonConfirmChangeQuestionnaire.showOriginal()
            }
        }
    }

    private fun addChip(chipId: Int, chipText: String) {
        Chip(this).apply {
            id = chipId
            text = chipText
            isClickable = true
            isCheckable = true
            setOnClickListener {
                if (!this.isChecked) {
                    questionnaireViewModel.removeInterest(chipText)
                    return@setOnClickListener
                }

                InterestBottomSheet.newInstance(chipText)
                    .show(supportFragmentManager, InterestBottomSheet.TAG)
            }
            binding.chipGroupInterests.addView(this)
        }
    }
}