package com.example.peoplefind.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityChangeQuestionnaireBinding
import com.example.peoplefind.domain.extension.onFailure
import com.example.peoplefind.domain.extension.onLoading
import com.example.peoplefind.domain.extension.onSuccess
import com.example.peoplefind.domain.model.Interest
import com.example.peoplefind.domain.model.QuestionnaireAddress
import com.example.peoplefind.presentation.util.createInterestDialog
import com.example.peoplefind.presentation.vm.ChangeQuestionnaireViewModel
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ChangeQuestionnaireActivity : AppCompatActivity() {
    private val changeQuestionnaireViewModel by viewModel<ChangeQuestionnaireViewModel>()
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
            changeQuestionnaireViewModel.addInterestDescription(interests.indexOf(it), "")
        }

        val interestsDescription = changeQuestionnaireViewModel.interestsDescriptionList.value

        buttonConfirmChangeQuestionnaire.setOnClickListener {
            changeQuestionnaireViewModel.updateQuestionnaire(
                name = editTextName.text.toString(),
                surname = "",
                birthDate = editTextAge.text.toString(),
                address = QuestionnaireAddress(
                    region = "",
                    city = editTextCity.text.toString(),
                    street = "",
                    numberOfHome = ""
                ),
                interests = listOf(
                    Interest(name = interests[0], description = interestsDescription?.get(0) ?: ""),
                    Interest(name = interests[1], description = interestsDescription?.get(1) ?: ""),
                    Interest(name = interests[2], description = interestsDescription?.get(2) ?: "")
                )
            )
        }

        changeQuestionnaireViewModel.updateQuestionnaireResult.observe(this@ChangeQuestionnaireActivity) { result ->
            result.onLoading {
                skeletonConfirmChangeQuestionnaire.showSkeleton()
            }.onSuccess {
                skeletonConfirmChangeQuestionnaire.showOriginal()
                finish()
            }.onFailure { message, error ->
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
                    changeQuestionnaireViewModel.removeInterestDescription(chipId)
                    return@setOnClickListener
                }

                val dialog = createInterestDialog(chipText)
                val interestDialog = dialog.first.show()
                val interestDialogBinding = dialog.second
                interestDialogBinding.buttonConfirmDescription.setOnClickListener {
                    changeQuestionnaireViewModel.addInterestDescription(
                        chipId, interestDialogBinding.editTextInterestDescription.text.toString()
                    )
                    interestDialog.dismiss()
                    Timber.d("Interest description: ${interestDialogBinding.editTextInterestDescription.text}")
                }
            }
            binding.chipGroupInterests.addView(this)
        }
    }
}