package com.example.peoplefind.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityQuestionnaireBinding
import com.example.peoplefind.domain.extension.onFailure
import com.example.peoplefind.domain.extension.onLoading
import com.example.peoplefind.domain.extension.onSuccess
import com.example.peoplefind.domain.model.QuestionnaireAddress
import com.example.peoplefind.presentation.util.convertAgeToIsoDateTime
import com.example.peoplefind.presentation.vm.InterestViewModel
import com.example.peoplefind.presentation.vm.QuestionnaireViewModel
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionnaireActivity : AppCompatActivity() {
    private val questionnaireViewModel by viewModel<QuestionnaireViewModel>()
    private val interestViewModel by viewModels<InterestViewModel>()
    private lateinit var binding: ActivityQuestionnaireBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaireBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        val interestsNames = resources.getStringArray(R.array.interests)
        interestsNames.forEach {
            addChip(interestsNames.indexOf(it), it)
            questionnaireViewModel.addInterest(it, "")
        }

        interestViewModel.interest.observe(this@QuestionnaireActivity) {
            questionnaireViewModel.addInterest(name = it.name, description = it.description)
        }

        buttonNext.setOnClickListener {
            questionnaireViewModel.fillQuestionnaire(
                name = editTextName.text.toString(),
                surname = "",
                birthDate = convertAgeToIsoDateTime(editTextAge.text.toString()),
                address = QuestionnaireAddress(
                    region = "Россия",
                    city = editTextCity.text.toString(),
                    street = "",
                    numberOfHome = ""
                ),
                interests = questionnaireViewModel.interests.value!!
            )
        }

        questionnaireViewModel.fillQuestionnaireResult.observe(this@QuestionnaireActivity) { result ->
            result.onLoading {
                skeletonNext.showSkeleton()
            }.onSuccess {
                skeletonNext.showOriginal()
                val intent = Intent(this@QuestionnaireActivity, MainActivity::class.java)
                startActivity(intent)
            }.onFailure { _, _ ->
                skeletonNext.showOriginal()
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